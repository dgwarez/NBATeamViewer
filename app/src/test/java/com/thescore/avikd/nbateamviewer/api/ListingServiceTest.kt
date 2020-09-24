package com.thescore.avikd.nbateamviewer.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ListingServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ListingService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ListingService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestTeams() {
        runBlocking {
            enqueueResponse("input.json")
            val resultResponse = service.getSets().body()

            val request = mockWebServer.takeRequest()
            assertNotNull(resultResponse)
            assertThat(request.path, `is`("/scoremedia/nba-team-viewer/master/input.json"))
        }
    }

    @Test
    fun getTeamsResponse() {
        runBlocking {
            enqueueResponse("input.json")
            val resultResponse = service.getSets().body()
            val teams = resultResponse!!

            assertThat(teams.size, `is`(3))
        }
    }

    @Test
    fun getTeamItem() {
        runBlocking {
            enqueueResponse("input.json")
            val resultResponse = service.getSets().body()
            val teams = resultResponse!!
            val team = teams[0]
            val player = team.players[0]
            assertThat(team.wins, `is`(45))
            assertThat(team.losses, `is`(20))
            assertThat(team.id, `is`(1))
            assertThat(team.players.size, `is`(17))
            assertThat(team.fullName, `is`("Boston Celtics"))

            assertThat(player.id, `is`(37729))
            assertThat(player.firstName, `is`("Kadeem"))
            assertThat(player.lastName, `is`("Allen"))
            assertThat(player.position, `is`("SG"))
            assertThat(player.number, `is`(45))
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
                source.readString(Charsets.UTF_8))
        )
    }
}
