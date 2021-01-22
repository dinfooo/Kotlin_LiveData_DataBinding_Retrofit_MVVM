package com.dammak.mahdi.di

import com.dammak.mahdi.ui.DetailsFavouriteFragment
import com.dammak.mahdi.ui.FavouritesListFragment
import com.dammak.mahdi.ui.MainActivity
import dagger.Subcomponent

// Classes annotated with @ActivityScope will have a unique instance in this Component
@ActivityScope
@Subcomponent(modules = [FavouritesListModule::class])
interface FavouriteComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavouriteComponent
    }

    // Classes that can be injected by this Component
    fun inject(fragment: FavouritesListFragment)
    fun inject(activity: MainActivity)
    fun inject(fragment: DetailsFavouriteFragment)
}
