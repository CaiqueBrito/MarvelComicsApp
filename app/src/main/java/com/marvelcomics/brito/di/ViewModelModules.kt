package com.marvelcomics.brito.di

import com.marvelcomics.brito.viewmodel.character.CharacterViewModel
import com.marvelcomics.brito.viewmodel.comic.ComicViewModel
import com.marvelcomics.brito.viewmodel.series.SeriesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModules {
    val viewModels = module {
        viewModel { CharacterViewModel(get(), Dispatchers.IO) }
        viewModel { ComicViewModel(get(), Dispatchers.IO) }
        viewModel { SeriesViewModel(get(), Dispatchers.IO) }
    }
}
