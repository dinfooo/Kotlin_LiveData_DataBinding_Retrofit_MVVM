package com.dammak.mahdi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dammak.mahdi.domain.Favourite
import com.dammak.mahdi.databinding.FavouriteViewItemBinding

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class FavouriteAdapter : ListAdapter<Favourite, FavouriteAdapter.FavouriteViewHolder>(
    DiffCallback
) {

    /**
     * The FavouriteViewHolder constructor takes the binding variable from the associated
     * FavouriteViewItem, which nicely gives it access to the full [Favourite] information.
     */
    class FavouriteViewHolder(private var binding: FavouriteViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(favourite: Favourite) {
            binding.favourite = favourite
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Favourite]
     * has been updated.
     */
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Favourite>() {
            override fun areItemsTheSame(oldItem: Favourite, newItem: Favourite): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Favourite, newItem: Favourite): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(
            FavouriteViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}