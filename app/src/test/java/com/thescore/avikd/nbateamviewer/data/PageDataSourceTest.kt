package com.thescore.avikd.nbateamviewer.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thescore.avikd.nbateamviewer.util.mock
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.*
import com.thescore.avikd.nbateamviewer.util.DomainObjectFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class PageDataSourceTest {

    private lateinit var pageDataSource: PageDataSource
    private var dataSource: RemoteDataSource = mock()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        pageDataSource = spy(PageDataSource(dataSource, coroutineScope))
    }

    @Test
    fun testFetchDataSortByWins() {
        val teamList = DomainObjectFactory.createTeamLists(5)
        val response: Result<List<Team>> = Result<List<Team>>(data = teamList, status = Result.Status.SUCCESS, message = "Test")
        val resultsToBeComparedWith = response.data?.sortedWith( compareByDescending {it.wins})
        val teamListSorted = pageDataSource.sortReceivedData(response, SortMethod.WINS)
        assert(teamListSorted.equals(resultsToBeComparedWith))
    }

    @Test
    fun testFetchDataSortByLosses() {
        val teamList = DomainObjectFactory.createTeamLists(5)
        val response: Result<List<Team>> = Result<List<Team>>(data = teamList, status = Result.Status.SUCCESS, message = "Test")
        val resultsToBeComparedWith = response.data?.sortedWith( compareByDescending {it.losses})
        val teamListSorted = pageDataSource.sortReceivedData(response, SortMethod.LOSSES)
        assert(teamListSorted.equals(resultsToBeComparedWith))
    }
}