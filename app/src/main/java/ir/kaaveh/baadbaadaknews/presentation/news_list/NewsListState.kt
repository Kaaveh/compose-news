package ir.kaaveh.baadbaadaknews.presentation.news_list

import ir.kaaveh.baadbaadaknews.domain.model.Article

data class NewsListState(
    val isLoading: Boolean = false,
    val news: List<Article> = listOf(),
    val error: String = "",
)