package br.com.meumetro.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Devices")
data class Device(
        @Id
        var id: String? = null,
        var idDevice: String? = null,
        var tokenDevice: String? = null,
        var creationDate: String? = null
)