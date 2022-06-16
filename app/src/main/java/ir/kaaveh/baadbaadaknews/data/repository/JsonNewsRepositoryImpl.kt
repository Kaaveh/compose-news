package ir.kaaveh.baadbaadaknews.data.repository

import ir.kaaveh.baadbaadaknews.data.local.FavoriteNewsDao
import ir.kaaveh.baadbaadaknews.data.mapper.toArticle
import ir.kaaveh.baadbaadaknews.data.mapper.toFavoriteNewsDto
import ir.kaaveh.baadbaadaknews.data.remote.NewsAPI
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.JsonNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JsonNewsRepositoryImpl @Inject constructor(
    private val api: NewsAPI,
    private val dao: FavoriteNewsDao
) : JsonNewsRepository {

    override suspend fun getJsonNews(): List<Article> =
        api.getBreakingNews().articleDtos.map { it.toArticle() }

    override suspend fun insertFavoriteNews(news: Article) =
        dao.insertFavoriteNews(news = news.toFavoriteNewsDto())

    override suspend fun removeFavoriteNews(news: Article) =
        dao.deleteFavoriteNews(news = news.toFavoriteNewsDto())

    override suspend fun isFavoriteNews(news: Article): Boolean =
        dao.isFavoriteNews(
            title = news.title,
            source = news.source
        )

    override suspend fun getAllFavoriteNews(): Flow<List<Article>> =
        dao.getAllFavoriteNews().map { list -> list.map { it.toArticle() } }

}