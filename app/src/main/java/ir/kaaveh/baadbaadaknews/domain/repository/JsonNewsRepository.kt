package ir.kaaveh.baadbaadaknews.domain.repository

import ir.kaaveh.baadbaadaknews.domain.model.Article

interface JsonNewsRepository {

    suspend fun getJsonNews(): List<Article>

    suspend fun insertFavoriteNews(newsTitle: String)

    suspend fun removeFavoriteNews(newsTitle: String)

    suspend fun isFavoriteNews(newsTitle: String): Boolean

}