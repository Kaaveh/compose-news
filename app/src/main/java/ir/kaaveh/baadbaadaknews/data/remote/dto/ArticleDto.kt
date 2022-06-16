package ir.kaaveh.baadbaadaknews.data.remote.dto

data class ArticleDto(
    val author: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String,
)