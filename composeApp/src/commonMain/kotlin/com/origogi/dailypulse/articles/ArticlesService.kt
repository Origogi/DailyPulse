package com.origogi.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(
    private val httpClient : HttpClient
) {

    private val country = "kr"
    private val query = "robinhood"
    private val apiKey = "9d6fee39e5f94fa0826366adf7f193a7"

    suspend fun fetchArticles() : List<ArticleRaw> {
        val response : ArticlesResponse = httpClient.get(
            urlString = "https://newsapi.org/v2/everything?q=$query?&from=2025-10-2&sortBy=publishedAt&apiKey=$apiKey"
        ).body()


        return response.articles
    }
}