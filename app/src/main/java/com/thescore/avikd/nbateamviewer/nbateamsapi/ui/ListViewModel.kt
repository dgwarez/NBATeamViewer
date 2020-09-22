package com.thescore.avikd.nbateamviewer.nbateamsapi.ui

import androidx.lifecycle.ViewModel
import com.thescore.avikd.nbateamviewer.di.CoroutineScropeIO
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.ListViewRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * The ViewModel for [ListFragment].
 */
class ListViewModel @Inject constructor(private val repository: ListViewRepository,
                                        @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope)
    : ViewModel() {

    var connectivityAvailable: Boolean = false

    val listView by lazy {
        repository.observePagedSets(
                connectivityAvailable, ioCoroutineScope)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
