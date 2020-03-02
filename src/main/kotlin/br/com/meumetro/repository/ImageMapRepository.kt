package br.com.meumetro.repository

import br.com.meumetro.model.MetropolitanMap
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageMapRepository : ReactiveCrudRepository<MetropolitanMap, String>