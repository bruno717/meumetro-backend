package br.com.meumetro.model.dto

data class MessageDTO(
        var title: String = String(),
        var simpleDescription: String = String(),
        var lines: List<LineDTO> = emptyList(),
        var type: Int = 0,
        var date: String = String()
)