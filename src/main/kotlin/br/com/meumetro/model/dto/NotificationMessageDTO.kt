package br.com.meumetro.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NotificationMessageDTO(
        @JsonProperty("data")
        var message: MessageDTO = MessageDTO(),

        @JsonProperty("registration_ids")
        var deviceList: List<String> = emptyList()
)