package com.thescore.avikd.nbateamviewer.nbateamsapi.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PageDataSourceFactory @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val scope: CoroutineScope) : DataSource.Factory<String, Team>() {

    private val liveData = MutableLiveData<PageDataSource>()

    override fun create(): PageDataSource {
        val source = PageDataSource(dataSource, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 30

        fun pagedListConfig() = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()
    }

}