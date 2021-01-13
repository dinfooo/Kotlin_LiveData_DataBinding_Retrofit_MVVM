package data.source

import com.dammak.mahdi.database.DatabaseFavourite
import com.dammak.mahdi.database.asDatabaseFavourite
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.domain.Image
import com.dammak.mahdi.repository.FavouriteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FavouriteRepositoryTest{
    private val databaseFavourite = DatabaseFavourite("2018-10-27T03:56:29.000Z", 994,
        Image("7ci","https://cdn2.thecatapi.com/images/7ci.jpg"))

    private val favourite1 = Favourite("2018-10-27T03:56:30.000Z", 995,
        Image("MTYzMzg5MQ1","https://cdn2.thecatapi.com/images/MTYzMzg5MQ1.jpg"))
    private val favourite2 = Favourite("2018-10-28T03:56:30.000Z", 996,
        Image("MTYzMzg5MQ2","https://cdn2.thecatapi.com/images/MTYzMzg5MQ2.jpg"))
    private val favourite3 = Favourite("2018-10-29T03:56:30.000Z", 997,
        Image("MTYzMzg5MQ3","https://cdn2.thecatapi.com/images/MTYzMzg5MQ3.jpg"))

    private val remoteFavourites = listOf(favourite1, favourite2,favourite3).sortedBy { it.id }
    private val localFavourites = listOf(databaseFavourite).sortedBy { it.id }

    private lateinit var favouritesRemoteDataSource: FakeFavouritesRemoteDataSource
    private lateinit var favouritesLocalDataSource: FakeFavouritesLocalDataSource

    // Class under test
    private lateinit var favouritesRepository: FavouriteRepository

    @Before
    fun createRepository() {
        favouritesRemoteDataSource = FakeFavouritesRemoteDataSource(remoteFavourites.toMutableList())
        favouritesLocalDataSource = FakeFavouritesLocalDataSource(localFavourites.toMutableList())
        // Get a reference to the class under test
        favouritesRepository = FavouriteRepository(
            favouritesLocalDataSource,favouritesRemoteDataSource
        )
    }

    @Test
    fun refreshFavouriteFromRemoteDataSource() = runBlockingTest {
        assertNotEquals(favouritesLocalDataSource.getFavourites().value,
            favouritesRemoteDataSource.getAllFavourites().asDatabaseFavourite())
        favouritesRepository.refreshFavourite()
        assertThat(favouritesLocalDataSource.getFavourites().value, IsEqual(
            favouritesRemoteDataSource.getAllFavourites().asDatabaseFavourite()))
    }

}