package com.dammak.mahdi.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dammak.mahdi.FavouriteApplication
import com.dammak.mahdi.R
import com.dammak.mahdi.database.AppDatabase
import com.dammak.mahdi.database.FavouritesLocalDataSourceImp
import com.dammak.mahdi.databinding.FragmentFavouriteListBinding
import com.dammak.mahdi.network.Api
import com.dammak.mahdi.repository.FavouriteRepository
import com.dammak.mahdi.viewmodels.FavouritesListViewModel
import javax.inject.Inject


/**
 * A simple [Fragment] that display favourites
 */
class FavouritesListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavouritesListViewModel> { viewModelFactory }

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

    override fun onAttach(context: Context) {
        (requireActivity().application as FavouriteApplication).appComponent
            .favouriteListComponent().create().inject(this)
        super.onAttach(context)
    }

}