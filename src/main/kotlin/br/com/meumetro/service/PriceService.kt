package br.com.meumetro.service

import br.com.meumetro.model.dto.PriceDTO

interface PriceService {

    fun getPrice(): PriceDTO

}