package com.origogi.dailypulse.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.origogi.dailypulse.articles.ArticleViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticleViewModel(get()) }
}