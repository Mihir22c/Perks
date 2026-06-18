package org.example.project.data.remote

import kotlinx.serialization.Serializable
import org.example.project.Offer

@Serializable
data class ProductsResponse(val products: List<OfferDto>)

@Serializable
data class OfferDto(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String
)