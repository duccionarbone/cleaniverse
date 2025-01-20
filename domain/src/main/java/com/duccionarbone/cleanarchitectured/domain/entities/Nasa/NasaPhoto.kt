package com.duccionarbone.cleanarchitectured.domain.entities.Nasa

import kotlinx.serialization.Serializable


@Serializable
data class NasaPhotos(
    val collection: Collection,
)

@Serializable
data class Collection(
    val version: String,
    val href: String,
    val items: List<NasaPhoto>,
    val metadata: Metadata,
    val links: List<Link>
)

@Serializable
data class NasaPhoto(
    val href: String,
    val data: List<Data>,
    val links: List<Link>
)
@Serializable
data class Data(
    val center: String,
    val title: String,
    val nasa_id: String,
    val date_created: String,
    val keywords: List<String>,
    val media_type: String,
    val description_508: String,
    val secondary_creator: String,
    val description: String
)

@Serializable
data class Link(
    val href: String,
    val rel: String,
    val render: String
)

@Serializable
data class Metadata(
    val total_hits: Int
)