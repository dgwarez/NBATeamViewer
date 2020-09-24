package com.thescore.avikd.nbateamviewer.nbateamsapi.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.thescore.avikd.nbateamviewer.testing.OpenForTesting
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
@OpenForTesting
class ListViewRepository @Inject constructor(private val NBATeamsAPIRemoteDataSource: RemoteDataSource) {

    fun observePagedSets(
        connectivityAvailable: Boolean,
        coroutineScope: CoroutineScope
    ) =
        if (connectivityAvailable) observeRemotePagedSets(coroutineScope)
        else observeRemotePagedSets(coroutineScope)//Can add offline support here

    fun observeRemotePagedSets(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<Team>> {
        val dataSourceFactory = PageDataSourceFactory(NBATeamsAPIRemoteDataSource, ioCoroutineScope)
        return LivePagedListBuilder(
            dataSourceFactory,
            PageDataSourceFactory.pagedListConfig()
        ).build()
    }
}

