package com.thescore.avikd.nbateamviewer.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thescore.avikd.nbateamviewer.api.ListingService
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.ListViewRepository
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.RemoteDataSource
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*


@RunWith(JUnit4::class)
class ListViewRepositoryTest {
    private lateinit var repository: ListViewRepository
    private val service = mock(ListingService::class.java)
    private val remoteDataSource = RemoteDataSource(service)

    @Before
    fun init() {
        repository = spy(ListViewRepository(remoteDataSource))
        doReturn(MutableLiveData<Team>() as LiveData<Team>).`when`<ListViewRepository>(repository).observeRemotePagedSets(coroutineScope)
    }

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Test
    fun loadTeamsFromNetwork() {
        runBlocking {
            repository.observePagedSets(connectivityAvailable = true, coroutineScope = coroutineScope)
            verify(repository, atLeastOnce()).observeRemotePagedSets(coroutineScope)
        }
    }

}