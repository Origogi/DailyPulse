package com.origogi.dailypulse.articles

class ArticlesUseCase(
    private val service: ArticlesService
) {
    suspend operator fun invoke(): List<Article> {
        return service.fetchArticles().map { it.toArticle() }
    }

}