package com.dammak.mahdi.di

import com.dammak.mahdi.ui.FavouritesListFragment
import dagger.Subcomponent

@Subcomponent(modules = [FavouritesListModule::class])
interface FavouritesListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavouritesListComponent
    }

    fun inject(fragment: FavouritesListFragment)
}
