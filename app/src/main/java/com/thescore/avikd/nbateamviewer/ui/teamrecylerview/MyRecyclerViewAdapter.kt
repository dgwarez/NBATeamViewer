package com.thescore.avikd.nbateamviewer.ui.teamrecylerview
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thescore.avikd.nbateamviewer.BR
import com.thescore.avikd.nbateamviewer.R
import com.thescore.avikd.nbateamviewer.databinding.ItemRowBinding
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Player


class MyRecyclerViewAdapter(
    private val dataModelList: List<Player>,
    ctx: Context?
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder?>(), CustomClickListener {
    private val context: Context? = ctx
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_row, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player: Player = dataModelList[position]
        holder.itemRowBinding.model = player
        holder.bind(player)
        holder.itemRowBinding.itemClickListener = this
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

    inner class ViewHolder(var itemRowBinding: ItemRowBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {
        fun bind(obj: Player?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }

    }

    override fun cardClicked(f: Player?) {
        Toast.makeText(
            context, "You clicked " + f?.firstName,
            Toast.LENGTH_LONG
        ).show()
    }

}


