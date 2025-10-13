package com.origogi.dailypulse

import com.origogi.dailypulse.articles.ArticleRaw
import com.origogi.dailypulse.articles.ArticlesService

class ArticlesRepository(
    private val articlesDataSource: ArticlesDataSource,
    private val articlesService: ArticlesService
) {

    suspend fun getArticles(forceFetch : Boolean = false) : List<ArticleRaw> {
        if (forceFetch) {
            println("Force fetching from remote")
            val remoteArticles = articlesService.fetchArticles()
            articlesDataSource.clearArticles()
            articlesDataSource.insertArticles(remoteArticles)
            return remoteArticles
        }

        val localArticles = articlesDataSource.getAllArticles()
        println("Local articles count: ${localArticles.size}")
        return localArticles.ifEmpty {
            println("Fetching from remote")
            val remoteArticles = articlesService.fetchArticles()
            articlesDataSource.insertArticles(remoteArticles)
            remoteArticles
        }

    }
}