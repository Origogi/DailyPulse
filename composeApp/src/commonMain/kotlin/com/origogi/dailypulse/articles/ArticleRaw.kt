package com.origogi.dailypulse.articles

import com.origogi.dailypulse.daysAgo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleRaw(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val desc: String? = "",
    @SerialName("publishedAt")
    val date: String,
    @SerialName("urlToImage")
    val imageUrl: String? = ""
)

fun ArticleRaw.toArticle(): Article {
    return Article(
        title = title,
        description = desc ?: "",
        date = date.daysAgo(),
        imageUrl = imageUrl ?: ""
    )
}
