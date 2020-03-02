package br.com.meumetro.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ImageMap")
data class MetropolitanMap(
        @Id
        val id: String,
        val map: String,
        val modificationDate: String
)