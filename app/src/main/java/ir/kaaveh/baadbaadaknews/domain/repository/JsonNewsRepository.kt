package ir.kaaveh.baadbaadaknews.domain.repository

import ir.kaaveh.baadbaadaknews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface JsonNewsRepository {

    suspend fun getJsonNews(): List<Article>

    suspend fun insertFavoriteNews(news: Article)

    suspend fun removeFavoriteNews(news: Article)

    suspend fun isFavoriteNews(news: Article): Boolean

    suspend fun getAllFavoriteNews(): Flow<List<Article>>

}