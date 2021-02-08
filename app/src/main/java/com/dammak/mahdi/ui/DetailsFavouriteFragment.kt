package com.dammak.mahdi.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dammak.mahdi.FavouriteApplication
import com.dammak.mahdi.R
import com.dammak.mahdi.databinding.FragmentDetailsFavouriteBinding
import com.dammak.mahdi.viewmodels.FavouriteViewModel
import kotlinx.android.synthetic.main.favourite_view_item.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFavouriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavouriteViewModel> { viewModelFactory }
    private lateinit var binding: FragmentDetailsFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details_favourite, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigateUp()
        }
        getSelectedFavourites()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is MainActivity)
            (activity as MainActivity).favouriteComponent.inject(this)
        else
            (requireActivity().application as FavouriteApplication).appComponent
                .favouriteComponent().create().inject(this)
    }

    private fun getSelectedFavourites() {
        viewModel.navigateToDetails.value?.let {
            binding.textviewIdImage.text = it.peekContent().id.toString()
        }
    }
}