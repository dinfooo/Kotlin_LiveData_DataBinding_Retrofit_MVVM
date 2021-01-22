package com.dammak.mahdi.di

import androidx.lifecycle.ViewModel
import com.dammak.mahdi.viewmodels.FavouriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavouritesListModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    abstract fun bindViewModel(viewmodel: FavouriteViewModel): ViewModel
}
