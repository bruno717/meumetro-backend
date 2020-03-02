package br.com.meumetro.controller

import br.com.meumetro.model.dto.PriceDTO
import br.com.meumetro.service.PriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("price")
class PriceController @Autowired constructor(private val service: PriceService) {

    @GetMapping
    fun getPrice(): PriceDTO {
        return service.getPrice()
    }

}