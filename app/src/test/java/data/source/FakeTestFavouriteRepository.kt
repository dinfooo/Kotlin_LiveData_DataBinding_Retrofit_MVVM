package data.source

import androidx.lifecycle.MutableLiveData
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.repository.IFavouriteRepository
import kotlinx.coroutines.runBlocking

class FakeTestFavouriteRepository : IFavouriteRepository {

    var favouriteServiceData: MutableList<Favourite> = mutableListOf()

    override val favourite: MutableLiveData<List<Favourite>>
        get() = MutableLiveData(favouriteServiceData)

    override suspend fun refreshFavourite() {
        favourite.value = favouriteServiceData
    }

    fun addFavourite(vararg favourites: Favourite) {
        for (favourite in favourites) {
            favouriteServiceData.add(favourite)
        }
        runBlocking { refreshFavourite() }
    }
}
