package data.source

import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.network.FavouritesRemoteDataSource

class FakeFavouritesRemoteDataSource(
    var favourites: MutableList<Favourite>? = mutableListOf()
) : FavouritesRemoteDataSource {

    override suspend fun getAllFavourites(): List<Favourite> {
        return favourites.orEmpty()
    }
}
