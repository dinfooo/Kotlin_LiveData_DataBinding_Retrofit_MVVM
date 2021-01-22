package com.dammak.mahdi.di

import android.content.Context
import com.dammak.mahdi.repository.IFavouriteRepository
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

/**
 * Main component for the application.
 *
 * See the `TestApplicationComponent` used in UI tests.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun favouriteComponent(): FavouriteComponent.Factory

    val favouriteRepository: IFavouriteRepository
}

@Module(
    subcomponents = [
        FavouriteComponent::class
    ]
)
object SubcomponentsModule