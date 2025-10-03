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
    val error: String? = null

)

class ArticleViewModel : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState())
    val articleState: StateFlow<ArticleState> = _articleState.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            _articleState.update { it.copy(isLoading = true) }

            _articleState.update {
                ArticleState(
                    articles = fetchArticles(),
                    isLoading = false
                )
            }
        }
    }

    private suspend fun fetchArticles(): List<Article> {
        delay(500)
        return mockArticles
    }
}

private val mockArticles = listOf(
    Article(
        title = "Robinhood Launches 24/7 Stock Trading",
        description = "Robinhood introduces round-the-clock trading for select stocks, revolutionizing retail investing.",
        date = "2025-10-02",
        imageUrl = "https://byline.network/wp-content/uploads/2021/02/robinhood_logo_main-1280x720.jpg"
    ),
    Article(
        title = "Robinhood Expands Cryptocurrency Offerings",
        description = "Platform adds support for 15 new cryptocurrencies including emerging altcoins.",
        date = "2025-10-01",
        imageUrl = "https://picsum.photos/seed/robinhood2/400/300"
    ),
    Article(
        title = "Robinhood Gold Membership Hits 2 Million Users",
        description = "Premium subscription service sees massive growth as users seek advanced trading features.",
        date = "2025-09-30",
        imageUrl = "https://picsum.photos/seed/robinhood3/400/300"
    ),
    Article(
        title = "Robinhood Partners with Major Banks",
        description = "Strategic partnerships announced to enhance banking services and credit card offerings.",
        date = "2025-09-29",
        imageUrl = "https://picsum.photos/seed/robinhood4/400/300"
    ),
    Article(
        title = "Robinhood Reports Record Trading Volume",
        description = "Q3 earnings show unprecedented user engagement and trading activity on the platform.",
        date = "2025-09-28",
        imageUrl = "https://picsum.photos/seed/robinhood5/400/300"
    )
)