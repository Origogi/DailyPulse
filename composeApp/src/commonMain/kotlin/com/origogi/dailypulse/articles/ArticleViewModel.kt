package com.origogi.dailypulse.articles

import com.origogi.dailypulse.BaseViewModel
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

class ArticleViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val articleState: StateFlow<ArticleState> = _articleState.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            _articleState.update { it.copy(isLoading = true) }

            try {
                val articles = useCase()
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