package br.com.meumetro.model.dto.advertisement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BodyDTO(
        @JsonProperty("available_quantity")
        val availableQuantity: Int,
        @JsonProperty("base_price")
        val basePrice: Int,
        @JsonProperty("buying_mode")
        val buyingMode: String?,
        @JsonProperty("category_id")
        val categoryId: String?,
        @JsonProperty("condition")
        val condition: String?,
        @JsonProperty("currency_id")
        val currencyId: String?,
        @JsonProperty("end_time")
        val endTime: String?,
        @JsonProperty("expiration_time")
        val expirationTime: String?,
        @JsonProperty("historical_start_time")
        val historicalStartTime: String?,
        @JsonProperty("id")
        val id: String,
        @JsonProperty("initial_quantity")
        val initialQuantity: Int,
        @JsonProperty("inventory_id")
        val inventoryId: String?,
        @JsonProperty("listing_type_id")
        val listingTypeId: String?,
        @JsonProperty("official_store_id")
        val officialStoreId: String?,
        @JsonProperty("original_price")
        val originalPrice: String?,
        @JsonProperty("permalink")
        val permalink: String?,
        @JsonProperty("pictures")
        val pictures: List<PictureDTO>?,
        @JsonProperty("price")
        val price: Float,
        @JsonProperty("secure_thumbnail")
        val secureThumbnail: String?,
        @JsonProperty("seller_id")
        val sellerId: Int,
        @JsonProperty("site_id")
        val siteId: String?,
        @JsonProperty("sold_quantity")
        val soldQuantity: Int,
        @JsonProperty("start_time")
        val startTime: String?,
        @JsonProperty("stop_time")
        val stopTime: String?,
        @JsonProperty("subtitle")
        val subtitle: String?,
        @JsonProperty("thumbnail")
        val thumbnail: String?,
        @JsonProperty("title")
        val title: String?
)
