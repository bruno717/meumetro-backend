package br.com.meumetro.service.impl

import br.com.meumetro.model.dto.PriceDTO
import br.com.meumetro.repository.PriceRepository
import br.com.meumetro.service.PriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PriceServiceImpl @Autowired constructor(
        private val priceRepository: PriceRepository
) : PriceService {

    override fun getPrice(): PriceDTO {
        return priceRepository.findAll()
                .map { PriceDTO(it) }
                .blockFirst() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, PRICE_NOT_FOUND)
    }

    companion object {
        private const val PRICE_NOT_FOUND = "price.not.found"
    }

}