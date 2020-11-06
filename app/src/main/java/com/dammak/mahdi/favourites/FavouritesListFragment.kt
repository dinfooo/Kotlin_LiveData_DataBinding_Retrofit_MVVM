package com.dammak.mahdi.favourites

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
import com.dammak.mahdi.databinding.FragmentFavouriteListBinding


/**
 * A simple [Fragment] that display favourites
 */
class FavouritesListFragment : Fragment() {
    /**
     * Lazily initialize our [FavouritesListViewModel].
     */
    private val viewModel: FavouritesListViewModel by lazy {
        ViewModelProvider(this).get(FavouritesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFavouriteListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_list, container, false)
        binding.favouritesListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewFavouritesList.adapter = FavouriteAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}