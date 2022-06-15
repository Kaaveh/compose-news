package ir.kaaveh.baadbaadaknews.data.remote

import ir.kaaveh.baadbaadaknews.common.Constants.Companion.API_KEY
import ir.kaaveh.baadbaadaknews.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse

}