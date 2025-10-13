package com.origogi.dailypulse.articles

import com.origogi.dailypulse.ArticlesRepository

class ArticlesUseCase(
    private val repository: ArticlesRepository
) {
    suspend operator fun invoke(): List<Article> {
        return repository.getArticles().map { it.toArticle() }

    }

}