package com.thescore.avikd.nbateamviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.ListViewRepository
import com.thescore.avikd.nbateamviewer.nbateamsapi.ui.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class ListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = mock(ListViewRepository::class.java)
    private var viewModel = ListViewModel(repository, coroutineScope)

    @Test
    fun doNotFetchWithoutObservers() {
        verify(repository, never()).observePagedSets(false, coroutineScope)
    }

    @Test
    fun doNotFetchWithoutObserverOnConnectionChange() {
        viewModel.connectivityAvailable = true
        verify(repository, never()).observePagedSets(true, coroutineScope)
    }

}