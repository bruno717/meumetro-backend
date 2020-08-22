package br.com.meumetro.controller

import br.com.meumetro.model.dto.advertisement.AdvertisementDTO
import br.com.meumetro.service.impl.AdvertisementServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("advertisement")
class AdvertisementController @Autowired constructor(
        private val service: AdvertisementServiceImpl
) {

    @GetMapping("items")
    fun getAdvertisements(@RequestParam("access_token") accessToken: String, @RequestParam("seller_id") sellerId: Long, response: HttpServletResponse): List<AdvertisementDTO> {
        return service.fetchAdvertisement(accessToken, sellerId)
                .blockingGet()
    }

}