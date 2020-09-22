package com.thescore.avikd.nbateamviewer.nbateamsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.thescore.avikd.nbateamviewer.R
import com.thescore.avikd.nbateamviewer.databinding.FragmentDetailsViewBinding
import com.thescore.avikd.nbateamviewer.di.Injectable
import com.thescore.avikd.nbateamviewer.di.injectViewModel
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Team
import com.thescore.avikd.nbateamviewer.ui.setTitle
import com.thescore.avikd.nbateamviewer.ui.teamrecylerview.MyRecyclerViewAdapter
import javax.inject.Inject


/**
 * A fragment representing a single ListView detail screen.
 */
class DetailsViewFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailsViewModel

    private val args: DetailsViewFragmentArgs by navArgs()
    private lateinit var set: Team

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.listItemData = args.listItemData

        val binding = DataBindingUtil.inflate<FragmentDetailsViewBinding>(
                inflater, R.layout.fragment_details_view, container, false).apply {
            lifecycleOwner = this@DetailsViewFragment
        }
        bindView(binding, viewModel.listItemData)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.teamRecyclerView.layoutManager = linearLayoutManager
        binding.teamRecyclerView.adapter =
            MyRecyclerViewAdapter(viewModel.listItemData.players, context)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun bindView(binding: FragmentDetailsViewBinding, listItemData: Team) {
        listItemData.apply {
            setTitle(resources.getString(R.string.details_title))
            binding.teamFullName.text= fullName
            binding.teamWins.text = String.format(resources.getString(R.string.wins_label), wins.toString())
            binding.teamLosses.text= String.format(resources.getString(R.string.losses_label), losses.toString())
            set = listItemData
        }
    }
}
