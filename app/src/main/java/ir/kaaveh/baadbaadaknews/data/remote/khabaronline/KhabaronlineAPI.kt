package ir.kaaveh.baadbaadaknews.data.remote.khabaronline

import ir.kaaveh.baadbaadaknews.data.remote.khabaronline.dto.KhabarOnlineResponse
import retrofit2.http.GET

interface KhabaronlineAPI {

    @GET("rss")
    suspend fun getLatestNews(): KhabarOnlineResponse

}