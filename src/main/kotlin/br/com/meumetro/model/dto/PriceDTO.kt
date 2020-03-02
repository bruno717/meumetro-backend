package br.com.meumetro.model.dto

import br.com.meumetro.model.Price

data class PriceDTO(
        val entire: String = String(),
        val half: String = String(),
        val integration: String = String()
) {

    constructor(price: Price) : this(
            price.entire,
            price.half,
            price.integration
    )

}