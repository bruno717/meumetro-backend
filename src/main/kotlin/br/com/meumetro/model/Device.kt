package br.com.meumetro.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Device")
data class Device(
        @Id
        val id: String? = null,
        val idDevice: String? = null,
        val tokenDevice: String? = null
)