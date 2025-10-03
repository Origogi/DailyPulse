package com.origogi.dailypulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.origogi.dailypulse.articles.ArticleViewModel
import com.origogi.dailypulse.screens.ArticlesScreen

class MainActivity : ComponentActivity() {

    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    ArticlesScreen(articleViewModel)
                }
            }
        }
    }
}
