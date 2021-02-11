package com.dammak.mahdi.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dammak.mahdi.FakeAndroidTestFavouriteRepository
import com.dammak.mahdi.FavouriteApplication
import com.dammak.mahdi.R
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.domain.Image
import com.dammak.mahdi.repository.IFavouriteRepository
import com.dammak.mahdi.util.addFavouriteBlocking
import com.dammak.mahdi.util.deleteAllFavouriteBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Large End-to-End test for the favourites module.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var repository: IFavouriteRepository

    @Before
    fun init() {
        repository = ApplicationProvider.getApplicationContext<FavouriteApplication>()
            .appComponent.favouriteRepository
        (repository as FakeAndroidTestFavouriteRepository).deleteAllFavouriteBlocking()
    }

    @Test
    fun displayDetails() {
        val favourite = Favourite(
            "2018-10-27T03:56:30.000Z", 995,
            Image("MTYzMzg5MQ1", "https://cdn2.thecatapi.com/images/MTYzMzg5MQ1.jpg")
        )

        (repository as FakeAndroidTestFavouriteRepository).addFavouriteBlocking(favourite)

        // start up MainActivity screen
        ActivityScenario.launch(MainActivity::class.java)

        // Click on the favourite on the list and verify that id image is displayed in Details
        onView(withId(R.id.recyclerView_favourites_list))
            .perform(
                RecyclerViewActions.actionOnItem<FavouriteAdapter.FavouriteViewHolder>(
                    hasDescendant(withText("995")), click()
                )
            )
        onView(withId(R.id.textview_id_image)).check(matches(withText("995")))
    }
}
