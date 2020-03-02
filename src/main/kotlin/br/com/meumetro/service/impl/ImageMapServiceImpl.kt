package br.com.meumetro.service.impl

import br.com.meumetro.model.dto.MetropolitanMapDTO
import br.com.meumetro.repository.ImageMapRepository
import br.com.meumetro.service.ImageMapService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ImageMapServiceImpl @Autowired constructor(
        private val imageMapRepository: ImageMapRepository
) : ImageMapService {

    override fun retrieveMetropolitanMap(modificationDateClientString: Long?): MetropolitanMapDTO {
        val metropolitanMapDTO = imageMapRepository.findAll()
                .map { MetropolitanMapDTO(it) }
                .blockFirst() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, MAP_NOT_FOUND)

        if (modificationDateClientString != null) {
            val modificationDateDataBase = DateTime.parse(metropolitanMapDTO.modificationDate).withHourOfDay(0)
                    .withMinuteOfHour(0).withSecondOfMinute(0).toDate()
            val modificationDateClient = DateTime(modificationDateClientString).withHourOfDay(0)
                    .withMinuteOfHour(0).withSecondOfMinute(0).toDate()
            if (modificationDateDataBase <= modificationDateClient) {
                throw ResponseStatusException(HttpStatus.NO_CONTENT)
            }
        }

        return metropolitanMapDTO
    }

    companion object {
        private const val MAP_NOT_FOUND = "map.not.found"
    }

}