package br.com.meumetro.repository

import br.com.meumetro.model.Price
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface PriceRepository : ReactiveCrudRepository<Price, String>