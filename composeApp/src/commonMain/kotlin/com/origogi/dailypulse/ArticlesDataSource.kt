package com.origogi.dailypulse

import com.origogi.dailypulse.articles.ArticleRaw
import com.origogi.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleRaw> {
        return database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw)
            .executeAsList()
    }

    fun insertArticles(articles: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { article ->
                database.dailyPulseDatabaseQueries.insertArticle(
                    title = article.title,
                    desc = article.desc,
                    date = article.date,
                    imageUrl = article.imageUrl
                )
            }
        }
    }

    fun clearArticles() {
        database.dailyPulseDatabaseQueries.removeAllArticles()
    }

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?
    ): ArticleRaw {
        return ArticleRaw(
            title = title,
            desc = desc,
            date = date,
            imageUrl = imageUrl
        )
    }
}
