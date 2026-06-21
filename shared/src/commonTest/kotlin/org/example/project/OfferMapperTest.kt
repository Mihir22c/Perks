package org.example.project

import org.example.project.data.remote.OfferDto
import org.example.project.data.remote.toDomain
import kotlin.test.Test
import kotlin.test.assertEquals

class OfferMapperTest {

    @Test
    fun dto_maps_to_domain() {
        val dto = OfferDto(
            id = 7,
            title = "Lipstick",
            description = "Red and bold",
            thumbnail = "https://img/lipstick.png",
        )

        val offer = dto.toDomain()

        assertEquals(7, offer.id)
        assertEquals("Lipstick", offer.title)
        assertEquals("Red and bold", offer.description)
        assertEquals("https://img/lipstick.png", offer.imageUrl)
    }
}