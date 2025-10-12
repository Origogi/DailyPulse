package com.origogi.dailypulse.di

import com.origogi.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    // Add shared modules here
    articlesModule,
    networkModule
)