package br.com.meumetro.repository

import br.com.meumetro.model.Line
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LineRepository : ReactiveCrudRepository<Line, String>