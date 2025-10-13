package com.origogi.dailypulse.di

import com.origogi.dailypulse.articles.ArticleViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    // iOS specific Koin initialization can be done here if needed
    val modules = sharedKoinModules + databaseModule

    startKoin {
        modules(modules)
    }

}


class ArticlesInjector : KoinComponent {
    val articlesViewModel : ArticleViewModel by inject()
}