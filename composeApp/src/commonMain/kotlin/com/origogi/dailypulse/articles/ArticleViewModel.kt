package com.origogi.dailypulse.articles

import com.origogi.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error : String? = null

)

class ArticleViewModel : BaseViewModel()  {

    private val _articleState: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val articleState: StateFlow<ArticleState> = _articleState.asStateFlow()

    init {
        getArticles()
    }


    private fun getArticles() {
        scope.launch {
            _articleState.update {
                delay(500)
                ArticleState()
            }
        }
    }
}