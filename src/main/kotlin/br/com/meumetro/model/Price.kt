package br.com.meumetro.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Prices")
data class Price(
        @Id
        val id: String,
        val entire: String,
        val half: String,
        val integration: String
)