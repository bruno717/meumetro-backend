package br.com.meumetro.model.dto.advertisement

data class AdvertisementIdsDTO(
        val paging: PagingDTO,
        var results: List<String>,
        val seller_id: String
) {
}