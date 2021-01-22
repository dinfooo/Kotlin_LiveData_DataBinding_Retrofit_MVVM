package viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.domain.Image
import com.dammak.mahdi.viewmodels.FavouriteViewModel
import data.source.FakeTestFavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.IsEqual
import org.junit.*

@ExperimentalCoroutinesApi
class FavouriteViewModelTest {

    private val dispatcher = TestCoroutineDispatcher()
    // Use a fake repository to be injected into the viewmodel
    private lateinit var favouritesRepository: FakeTestFavouriteRepository
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    // Subject under test
    private lateinit var favouriteViewModel: FavouriteViewModel

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

    @Before
    fun setupViewModel() {
        Dispatchers.setMain(dispatcher)
        // We initialise the favourites
        favouritesRepository = FakeTestFavouriteRepository()

        favouritesRepository.addFavourite(favourite1, favourite2, favourite3)

        favouriteViewModel = FavouriteViewModel(favouritesRepository)

    }

    @Test
    fun testGetListFavourite() {
        Assert.assertThat(
            favouriteViewModel.listFavourite.value,
            IsEqual(listOf(favourite1, favourite2, favourite3))
        )
    }

    @Test
    fun testGetStatus() {
        Assert.assertThat(
            favouriteViewModel.status.value,
            IsEqual(FavouriteViewModel.FavouritesApiStatus.DONE)
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}