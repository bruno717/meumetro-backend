package br.com.meumetro.service.impl

import br.com.meumetro.model.dto.advertisement.AdvertisementDTO
import br.com.meumetro.model.dto.advertisement.AdvertisementIdsDTO
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException

@Service
class AdvertisementServiceImpl @Autowired constructor(
        private val restTemplate: RestTemplate,
        private val objectMapper: ObjectMapper
) {

    private var advertisementIds = arrayListOf<String>()

    fun fetchAdvertisement(accessToken: String, sellerId: Long): Single<List<AdvertisementDTO>> {
        return fetchAdvertisementIds(sellerId, accessToken)
                .flatMap { fetchAdvertisementById(accessToken) }
    }

    fun fetchAdvertisementIds(
            sellerId: Long,
            accessToken: String
    ): Single<AdvertisementIdsDTO> {
        val status = "active"
        val limit = 100
        val total = advertisementIds.size

        var url = "https://api.mercadolibre.com/users/{seller_id}/items/search?status=${status}&access_token=${accessToken}&offset=${total}&limit=${limit}"
        url = url.replace("{seller_id}", sellerId.toString())
        val response = restTemplate.getForEntity(url, String::class.java)

        if (response.statusCode == HttpStatus.OK) {
            val advertisementIdsDTO = objectMapper.readValue<AdvertisementIdsDTO>(response.body, AdvertisementIdsDTO::class.java)
            return Single.just(advertisementIdsDTO)
                    .flatMap {
                        if (total < it.paging.total && it.paging.total > it.paging.limit) {
                            advertisementIds.addAll(it.results)
                            fetchAdvertisementIds(sellerId, accessToken)
                        } else {
                            if (advertisementIds.isNotEmpty()) {
                                it.results = advertisementIds
                            }
                            advertisementIds = it.results as ArrayList<String>
                            Single.just(it)
                        }
                    }
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, null)
    }

    private fun fetchAdvertisementById(accessToken: String): Single<List<AdvertisementDTO>> {
        var indexMap = 0
        val mapIds: HashMap<Int, ArrayList<String>> = hashMapOf()
        mapIds[indexMap] = arrayListOf()
        advertisementIds.forEach { id ->
            mapIds[indexMap]?.size?.let { size ->
                if (size < 20) {
                    mapIds[indexMap]?.add(id)
                } else {
                    indexMap += 1
                    mapIds[indexMap] = arrayListOf()
                    mapIds[indexMap]?.add(id)
                }
            }
        }

        val requestAdvertisementByIdList = mapIds.map {
            getRequestAdvertisementById(accessToken, it.value.toTypedArray())
        }
        val zip = Single.zip(requestAdvertisementByIdList) {
            val items = arrayListOf<AdvertisementDTO>()
            it.map { item -> item as ArrayList<AdvertisementDTO> }.map { item -> items.addAll(item) }
            print("Response: ${items.size}")
            return@zip items.toList()
        }
        return if (requestAdvertisementByIdList.isEmpty()) Single.just(listOf()) else zip
    }

    private fun getRequestAdvertisementById(accessToken: String, ids: Array<String>): Single<List<AdvertisementDTO>> {
        val idsString = ids.contentToString()
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
        val response = restTemplate.getForEntity(
                "https://api.mercadolibre.com/items?access_token=${accessToken}&ids=${idsString}",
                String::class.java
        )

        val type = objectMapper.typeFactory.constructCollectionType(List::class.java, AdvertisementDTO::class.java)

        if (response.statusCode == HttpStatus.OK) {
            val advertisements = objectMapper.readValue<List<AdvertisementDTO>>(response.body, type)
            return Single.just(advertisements)
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, null)
    }

}