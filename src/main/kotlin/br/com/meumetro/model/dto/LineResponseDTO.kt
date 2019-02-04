package br.com.meumetro.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LineResponseDTO(
        @JsonProperty("Status")
        val status: String? = null,

        @JsonProperty("Descricao")
        val description: String? = null,

        @JsonProperty("LinhaId")
        val lineId: Int? = null,

        @JsonProperty("Nome")
        val name: String? = null,

        @JsonProperty("Tipo")
        val type: String? = null,

        @JsonProperty("DataGeracao")
        val generationDate: String? = null
)