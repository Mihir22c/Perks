package org.example.project

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.remote.OffersApi
expect fun httpClientEngineFactory(): HttpClientEngineFactory<*>

fun createHttpClient(): HttpClient = HttpClient(httpClientEngineFactory()) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}
