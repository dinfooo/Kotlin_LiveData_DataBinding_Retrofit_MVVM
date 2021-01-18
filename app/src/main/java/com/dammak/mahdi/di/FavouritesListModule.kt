package com.dammak.mahdi.di

import androidx.lifecycle.ViewModel
import com.dammak.mahdi.viewmodels.FavouritesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavouritesListModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesListViewModel::class)
    abstract fun bindViewModel(viewmodel: FavouritesListViewModel): ViewModel
}
