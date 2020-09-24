package com.thescore.avikd.nbateamviewer.nbateamsapi.data

import androidx.paging.PageKeyedDataSource
import com.thescore.avikd.nbateamviewer.App
import com.thescore.avikd.nbateamviewer.data.Result
import com.thescore.avikd.nbateamviewer.testing.OpenForTesting
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Data source for kotlin news data through pagination via paging library
 */
class PageDataSource @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val scope: CoroutineScope) : PageKeyedDataSource<String, Team>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Team>) {
        fetchData(null, params.requestedLoadSize) {
            callback.onResult(it, null, null)
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Team>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, null)
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Team>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, null)
        }
    }

    private fun fetchData(page: String?, pageSize: Int, callback: (List<Team>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchSets(page, pageSize)
            if (response.status == Result.Status.SUCCESS) {
                val results = sortReceivedData(response, App.sortMethod)
                callback(results)
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    @OpenForTesting
    fun sortReceivedData(response: Result<List<Team>>, sortMethod: SortMethod?): List<Team> {
        var results = response.data!!
        sortMethod?.let {
            if (it == SortMethod.WINS) {
                results = results.sortedWith( compareByDescending {it.wins})
            } else if (it == SortMethod.LOSSES) {
                results = results.sortedWith( compareByDescending {it.losses})
            }
        }
        return results
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
    }

}