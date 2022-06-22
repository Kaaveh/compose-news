package ir.kaaveh.baadbaadaknews.data.remote.khabaronline.dto

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("enclosure")
    val imageNews: ImageNewsDto?,
    val author: String?,
    val link: String?,
    val description: String?,
    val guid: String?,
    val title: String?,
    @SerializedName("content:encoded")
    val contentEncoded: String?,
    val pubDate: String?
)