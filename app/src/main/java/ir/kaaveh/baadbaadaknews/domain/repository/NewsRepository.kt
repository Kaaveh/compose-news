package ir.kaaveh.baadbaadaknews.domain.repository

import ir.kaaveh.baadbaadaknews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getOrgNews(): List<Article>

    suspend fun getKhabaronlineNews(): List<Article>

    suspend fun insertFavoriteNews(news: Article)

    suspend fun removeFavoriteNews(news: Article)

    suspend fun isFavoriteNews(news: Article): Boolean

    fun getAllFavoriteNews(): Flow<List<Article>>

}