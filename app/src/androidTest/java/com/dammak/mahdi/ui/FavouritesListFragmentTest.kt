package com.dammak.mahdi.ui

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.dammak.mahdi.FakeAndroidTestFavouriteRepository
import com.dammak.mahdi.FavouriteApplication
import com.dammak.mahdi.R
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.domain.Image
import com.dammak.mahdi.repository.IFavouriteRepository
import com.dammak.mahdi.util.deleteAllFavouriteBlocking
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher.Companion.recyclerViewWithId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavouritesListFragmentTest {
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var repository: IFavouriteRepository

    @Before
    fun initRepository() {
        Dispatchers.setMain(dispatcher)
        repository = ApplicationProvider.getApplicationContext<FavouriteApplication>()
            .appComponent.favouriteRepository
        (repository as FakeAndroidTestFavouriteRepository).deleteAllFavouriteBlocking()
    }

    @After
    fun tearDown() = runBlockingTest {
        Dispatchers.resetMain()
    }


    @Test
    fun activeFavouritesListFragment_DisplayedInUi() = runBlockingTest {
        val favourite1 = Favourite(
            "2018-10-27T03:56:30.000Z", 995,
            Image("MTYzMzg5MQ1", "https://cdn2.thecatapi.com/images/MTYzMzg5MQ1.jpg")
        )
        val favourite2 = Favourite(
            "2018-10-28T03:56:30.000Z", 996,
            Image("MTYzMzg5MQ2", "https://cdn2.thecatapi.com/images/MTYzMzg5MQ2.jpg")
        )
        val favourite3 = Favourite(
            "2018-10-29T03:56:30.000Z", 997,
            Image("MTYzMzg5MQ3", "https://cdn2.thecatapi.com/images/MTYzMzg5MQ3.jpg")
        )

        (repository as FakeAndroidTestFavouriteRepository).addFavourite(
            favourite1,
            favourite2,
            favourite3
        )

        // WHEN - Details fragment launched to display favourites
        launchFragmentInContainer<FavouritesListFragment>(null, R.style.AppTheme)

        val recyclerViewMatcher: RecyclerViewMatcher =
            recyclerViewWithId(R.id.recyclerView_favourites_list)
        val recyclerView: Matcher<View> = withId(R.id.recyclerView_favourites_list)

        onView(recyclerView)
            .check(matches(isDisplayed()))

        onView(recyclerViewMatcher.viewHolderViewAtPosition(0, R.id.id_image))
            .check(matches(withText(favourite1.id.toString())))
        onView(recyclerViewMatcher.viewHolderViewAtPosition(1, R.id.id_image))
            .check(matches(withText(favourite2.id.toString())))
        onView(recyclerViewMatcher.viewHolderViewAtPosition(2, R.id.id_image))
            .check(matches(withText(favourite3.id.toString())))
    }

}
