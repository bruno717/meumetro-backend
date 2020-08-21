package br.com.meumetro.model.dto.advertisement

data class PagingDTO(
        val limit: Int,
        val offset: Int,
        val total: Int
)
