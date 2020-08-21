package br.com.meumetro.model.dto.advertisement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AdvertisementDTO(
        @JsonProperty("body")
        val body: BodyDTO,
        @JsonProperty("code")
        val code: Int
)