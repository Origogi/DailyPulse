package com.origogi.dailypulse.articles

import com.origogi.dailypulse.ArticlesRepository

class ArticlesUseCase(
    private val repository: ArticlesRepository
) {
    suspend operator fun invoke(
        forceFetch: Boolean = false
    ): List<Article> {
        return repository.getArticles(
            forceFetch = forceFetch
        ).map { it.toArticle() }

    }

}