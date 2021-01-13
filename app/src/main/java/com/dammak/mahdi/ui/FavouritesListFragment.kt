package com.dammak.mahdi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dammak.mahdi.R
import com.dammak.mahdi.database.AppDatabase
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.databinding.FragmentFavouriteListBinding
import com.dammak.mahdi.network.Api
import com.dammak.mahdi.repository.FavouriteRepository
import com.dammak.mahdi.viewmodels.FavouritesListViewModel



/**
 * A simple [Fragment] that display favourites
 */
class FavouritesListFragment : Fragment() {
    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */
    private val viewModel: FavouritesListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProvider(
            this, FavouritesListViewModel.Factory(
                FavouriteRepository(
                    FavouritesLocalDataSourceImp(
                        AppDatabase.getInstance(activity.application).favouriteDao
                    ),
                    Api.retrofitService
                )
            )
        )
            .get(FavouritesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFavouriteListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_list, container, false)
        binding.favouritesListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewFavouritesList.adapter =
            FavouriteAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}