package ir.kaaveh.baadbaadaknews.data.local

import androidx.room.Entity

@Entity(tableName = "favorites", primaryKeys = ["title", "source"])
data class FavoriteNewsDto(
    val title: String,
    val author: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val url: String,
    val urlToImage: String,
    val type: String = "JSON",
)