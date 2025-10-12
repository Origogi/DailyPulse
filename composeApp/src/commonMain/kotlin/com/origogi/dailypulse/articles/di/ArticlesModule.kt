package com.origogi.dailypulse.articles.di

import com.origogi.dailypulse.articles.ArticleViewModel
import com.origogi.dailypulse.articles.ArticlesService
import com.origogi.dailypulse.articles.ArticlesUseCase
import org.koin.dsl.module

val articlesModule = module {
    single {
        ArticlesService(get())
    }
    single {
        ArticlesUseCase(get())
    }
    single {
        ArticleViewModel(get())
    }
}