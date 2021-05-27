package br.com.meumetro.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LineResponseDTO(
        @JsonProperty("Status")
        var status: String? = null,

        @JsonProperty("Descricao")
        var description: String? = null,

        @JsonProperty("LinhaId")
        val lineId: Int? = null,

        @JsonProperty("Nome")
        val name: String? = null,

        @JsonProperty("Tipo")
        var type: String? = null,

        @JsonProperty("DataGeracao")
        var generationDate: String? = null
)