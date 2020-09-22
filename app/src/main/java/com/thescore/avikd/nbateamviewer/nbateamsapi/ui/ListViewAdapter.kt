package com.thescore.avikd.nbateamviewer.nbateamsapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thescore.avikd.nbateamviewer.databinding.ListItemBinding
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Team

/**
 * Adapter for the [RecyclerView] in [ListFragment].
 */
class ListViewAdapter : PagedListAdapter<Team, ListViewAdapter.ViewHolder>(ListViewtDiffCallback()) {

    private lateinit var recyclerView: RecyclerView

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = getItem(position)
        listItem?.let {
            holder.apply {
                bind(createOnClickListener(listItem), listItem)
                itemView.tag = listItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    private fun createOnClickListener(listItemData: Team): View.OnClickListener {
        return View.OnClickListener {
            val direction = ListFragmentDirections.actionToDetailFragment(listItemData)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(private val binding: ListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            listener: View.OnClickListener, item: Team?) {
            binding.apply {
                clickListener = listener
                listItem = item
                executePendingBindings()
            }
        }
    }
}

private class ListViewtDiffCallback : DiffUtil.ItemCallback<Team>() {

    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem == newItem
    }
}