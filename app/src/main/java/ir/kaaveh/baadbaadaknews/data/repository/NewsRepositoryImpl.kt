package ir.kaaveh.baadbaadaknews.data.repository

import ir.kaaveh.baadbaadaknews.data.local.FavoriteNewsDao
import ir.kaaveh.baadbaadaknews.data.mapper.toArticle
import ir.kaaveh.baadbaadaknews.data.mapper.toFavoriteNewsDto
import ir.kaaveh.baadbaadaknews.data.remote.khabaronline.KhabaronlineAPI
import ir.kaaveh.baadbaadaknews.data.remote.news_org.NewsAPI
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val orgAPI: NewsAPI,
    private val khabaronlineAPI: KhabaronlineAPI,
    private val dao: FavoriteNewsDao,
) : NewsRepository {

    override suspend fun getOrgNews(): List<Article> =
        orgAPI.getBreakingNews().articles.map { it.toArticle() }

    override suspend fun getKhabaronlineNews(): List<Article> =
        khabaronlineAPI.getLatestNews().rss?.channel?.item?.map { it.toArticle() } ?: listOf()

    override suspend fun insertFavoriteNews(news: Article) =
        dao.insertFavoriteNews(news = news.toFavoriteNewsDto())

    override suspend fun removeFavoriteNews(news: Article) =
        dao.deleteFavoriteNews(news = news.toFavoriteNewsDto())

    override suspend fun isFavoriteNews(news: Article): Boolean =
        dao.isFavoriteNews(
            title = news.title,
            source = news.source
        )

    override fun getAllFavoriteNews(): Flow<List<Article>> =
        dao.getAllFavoriteNews().map { list -> list.map { it.toArticle() } }

}