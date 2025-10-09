package com.origogi.dailypulse

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.origogi.dailypulse.articles.ArticleViewModel
import com.origogi.dailypulse.screens.AboutScreen
import com.origogi.dailypulse.screens.ArticlesScreen
import com.origogi.dailypulse.screens.Router

@Composable
fun AppScaffold(
    articleViewModel: ArticleViewModel
) {

    val navController = rememberNavController()

    Scaffold { paddings ->
        AppNavHost(
            navHostController = navController,
            articleViewModel = articleViewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        )
    }
}

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    articleViewModel: ArticleViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = Router.ARTICLES.route,
        modifier = modifier
    ) {
        composable(
            route = Router.ARTICLES.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(300)
                )
            }
        ) {
            ArticlesScreen(
                onAboutClick = {
                    navHostController.navigate(Router.ABOUT.route)
                },
                articlesViewModel = articleViewModel
            )
        }

        composable(
            route = Router.ABOUT.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(300)
                )
            }
        ) {
            AboutScreen(
                onUpButtonClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}