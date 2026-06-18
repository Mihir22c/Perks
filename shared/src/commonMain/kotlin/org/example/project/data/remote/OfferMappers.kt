package org.example.project.data.remote

import org.example.project.Offer

fun OfferDto.toDomain() = Offer(
    id = id,
    title = title,
    description = description,
    imageUrl = thumbnail
)