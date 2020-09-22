package com.thescore.avikd.nbateamviewer.nbateamsapi.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thescore.avikd.nbateamviewer.App
import com.thescore.avikd.nbateamviewer.R
import com.thescore.avikd.nbateamviewer.databinding.FragmentListViewBinding
import com.thescore.avikd.nbateamviewer.di.Injectable
import com.thescore.avikd.nbateamviewer.di.injectViewModel
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.SortMethod
import com.thescore.avikd.nbateamviewer.ui.VerticalItemDecoration
import com.thescore.avikd.nbateamviewer.ui.hide
import com.thescore.avikd.nbateamviewer.util.ConnectivityUtil
import javax.inject.Inject

class ListFragment : Fragment(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ListViewModel

    private val args: ListFragmentArgs by navArgs()


    private lateinit var binding: FragmentListViewBinding
    private val adapter: ListViewAdapter by lazy { ListViewAdapter() }
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val linearDecoration: RecyclerView.ItemDecoration by lazy {
        VerticalItemDecoration(
                resources.getDimension(R.dimen.margin_normal).toInt())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
        binding = DataBindingUtil.inflate<FragmentListViewBinding>(
            inflater, R.layout.fragment_list_view, container, false).apply {
            lifecycleOwner = this@ListFragment
        }
        context ?: return binding.root

        linearLayoutManager = LinearLayoutManager(activity)
        setLayoutManager()
        binding.recyclerView.adapter = adapter

        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: ListViewAdapter) {
        viewModel.listView.observe(viewLifecycleOwner) {
            binding.progressBar.hide()
            adapter.submitList(it)
        }
    }

    private fun setLayoutManager() {
        val recyclerView = binding.recyclerView

        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()
        }

        recyclerView.addItemDecoration(linearDecoration)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.scrollToPosition(scrollPosition)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_navigation, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_by_win -> {
                App.sortMethod = SortMethod.WINS
                viewModel.listView.value?.dataSource?.invalidate()
                true
            }

            R.id.sort_by_loss -> {
                App.sortMethod = SortMethod.LOSSES
                viewModel.listView.value?.dataSource?.invalidate()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
