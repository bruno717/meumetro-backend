package br.com.meumetro.model.dto.advertisement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PictureDTO(
        @JsonProperty("id")
        val id: String,
        @JsonProperty("max_size")
        val maxSize: String,
        @JsonProperty("quality")
        val quality: String,
        @JsonProperty("secure_url")
        val secureUrl: String,
        @JsonProperty("size")
        val size: String,
        @JsonProperty("url")
        val url: String
)
