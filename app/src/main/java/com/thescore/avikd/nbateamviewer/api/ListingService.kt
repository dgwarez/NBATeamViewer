package com.thescore.avikd.nbateamviewer.api

import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API access points
 */
interface ListingService {

    companion object {
        const val ENDPOINT = "https://raw.githubusercontent.com/"
    }

    @GET("scoremedia/nba-team-viewer/master/input.json")
    suspend fun getSets(@Query("pageNumber") pageLoadKey: String? = null,
                        @Query("pageSize") pageSize: Int? = null): Response<List<Team>>

}
