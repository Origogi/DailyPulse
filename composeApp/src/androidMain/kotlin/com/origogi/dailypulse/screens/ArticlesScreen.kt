package com.origogi.dailypulse.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.origogi.dailypulse.articles.Article
import com.origogi.dailypulse.articles.ArticleState
import com.origogi.dailypulse.articles.ArticleViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ArticlesScreen(
    onAboutClick: () -> Unit,
    articlesViewModel: ArticleViewModel = koinViewModel()
) {
    val state by articlesViewModel.articleState.collectAsState()

    ArticlesScreen(
        state = state,
        onAboutClick = onAboutClick
    )

}

@Composable
private fun ArticlesScreen(
    onAboutClick: () -> Unit,
    state: ArticleState
) {
    Column {
        AppBar(
            onAboutClick = onAboutClick
        )
        when {
            state.isLoading -> Loader()
            state.error != null -> ErrorMessage(state.error)
            state.articles.isNotEmpty() -> ArticlesListView(
                articles = state.articles
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(onAboutClick: () -> Unit) {
    TopAppBar(
        title = {
            Text("Articles")
        }
        ,
        actions = {
            IconButton(
                onClick = onAboutClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About"
                )
            }
        }

    )
}

@Composable
private fun ArticlesListView(
    articles: List<Article>
) {
    LazyColumn {
        items(articles) {
            ArticleItemView(article = it)
        }
    }
}

@Composable
private fun ArticleItemView(
    article: Article
) {
    val fallbackImageUrl = "https://static.toss.im/png-icons/securities/icn-sec-fill-NAS0DHQ0D-E0.png"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SubcomposeAsyncImage(
            model = article.imageUrl,
            contentDescription = article.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop,
            error = {
                SubcomposeAsyncImage(
                    model = fallbackImageUrl,
                    contentDescription = "Fallback image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = article.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = article.date,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
private fun Loader() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun ErrorMessage(
    error: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesScreenPreview() {
    MaterialTheme {
        ArticlesScreen(
            onAboutClick = {},
            state = ArticleState(
                articles = listOf(
                    Article(
                        title = "Sample Article 1",
                        description = "This is a sample article description",
                        date = "2025-10-04",
                        imageUrl = "https://picsum.photos/seed/sample1/400/300"
                    ),
                    Article(
                        title = "Sample Article 2",
                        description = "Another sample article description",
                        date = "2025-10-03",
                        imageUrl = "https://picsum.photos/seed/sample2/400/300"
                    )
                ),
                isLoading = false,
                error = null
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesScreenLoadingPreview() {
    MaterialTheme {
        ArticlesScreen(
            onAboutClick = {},
            state = ArticleState(
                articles = emptyList(),
                isLoading = true,
                error = null
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesScreenErrorPreview() {
    MaterialTheme {
        ArticlesScreen(
            onAboutClick = {},
            state = ArticleState(
                articles = emptyList(),
                isLoading = false,
                error = "Failed to load articles"
            )

        )
    }
}
