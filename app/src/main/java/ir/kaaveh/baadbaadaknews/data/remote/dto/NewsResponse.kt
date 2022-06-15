package ir.kaaveh.baadbaadaknews.data.remote.dto

data class NewsResponse(
    val articleDtos: List<ArticleDto>,
    val status: String,
    val totalResults: Int,
)
