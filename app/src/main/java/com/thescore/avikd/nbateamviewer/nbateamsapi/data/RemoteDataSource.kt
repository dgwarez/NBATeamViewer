package com.thescore.avikd.nbateamviewer.nbateamsapi.data

import com.thescore.avikd.nbateamviewer.testing.OpenForTesting
import com.thescore.avikd.nbateamviewer.api.BaseDataSource
import com.thescore.avikd.nbateamviewer.api.ListingService

import javax.inject.Inject

@OpenForTesting
class RemoteDataSource @Inject constructor(private val service: ListingService) : BaseDataSource() {

    suspend fun fetchSets(pageLoadKey: String?, pageSize: Int? = null)
            = getResult { service.getSets(pageLoadKey, pageSize) }
}
