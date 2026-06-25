package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.Offer

interface OffersApi {
    suspend fun getOffers(): List<Offer>
}

class KtorOffersApi(private val client: HttpClient) : OffersApi {
    override suspend fun getOffers(): List<Offer> =
        client.get("https://dummyjson.com/products")
            .body<ProductsResponse>()
            .products
            .map { it.toDomain() }
}