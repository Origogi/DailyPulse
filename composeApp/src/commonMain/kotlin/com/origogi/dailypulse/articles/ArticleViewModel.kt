package com.origogi.dailypulse.articles

import com.origogi.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null

)

class ArticleViewModel : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val articleState: StateFlow<ArticleState> = _articleState.asStateFlow()

    private val articlesUseCase: ArticlesUseCase

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = true
                    }
                )
            }
        }
        val articlesService = ArticlesService(httpClient)
        articlesUseCase = ArticlesUseCase(articlesService)

        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            _articleState.update { it.copy(isLoading = true) }

            try {
                val articles = articlesUseCase()
                _articleState.update {
                    ArticleState(
                        articles = articles,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _articleState.update {
                    ArticleState(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }
}