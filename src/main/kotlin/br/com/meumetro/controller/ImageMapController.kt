package br.com.meumetro.controller

import br.com.meumetro.model.dto.MetropolitanMapDTO
import br.com.meumetro.service.ImageMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.Query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("image-map")
class ImageMapController @Autowired constructor(private val service: ImageMapService) {

    @GetMapping
    fun retrieveMetropolitanMap(@RequestParam modificationDateClient: Long?): MetropolitanMapDTO {
        return service.retrieveMetropolitanMap(modificationDateClient)
    }

}