package com.origogi.dailypulse

import com.origogi.dailypulse.articles.ArticleRaw
import com.origogi.dailypulse.articles.ArticlesService

class ArticlesRepository(
    private val articlesDataSource: ArticlesDataSource,
    private val articlesService: ArticlesService
) {

    suspend fun getArticles() : List<ArticleRaw> {
        val localArticles = articlesDataSource.getAllArticles()
        return localArticles.ifEmpty {
            val remoteArticles = articlesService.fetchArticles()
            articlesDataSource.insertArticles(remoteArticles)
            remoteArticles
        }

    }
}