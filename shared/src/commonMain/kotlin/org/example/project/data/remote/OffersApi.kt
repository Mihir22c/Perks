package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.Offer

class OffersApi(private val client: HttpClient) {
    suspend fun getOffers(): List<Offer> =
        client.get("https://dummyjson.com/products")
            .body<ProductsResponse>()
            .products
            .map { it.toDomain() }
}