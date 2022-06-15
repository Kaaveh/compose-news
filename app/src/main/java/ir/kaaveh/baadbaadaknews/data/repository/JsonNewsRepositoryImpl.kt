package ir.kaaveh.baadbaadaknews.data.repository

import ir.kaaveh.baadbaadaknews.data.remote.NewsAPI
import ir.kaaveh.baadbaadaknews.data.remote.dto.toArticle
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.JsonNewsRepository
import javax.inject.Inject

class JsonNewsRepositoryImpl @Inject constructor(
    private val api: NewsAPI,
) : JsonNewsRepository {

    override suspend fun getJsonNews(): List<Article> =
        api.getBreakingNews().articleDtos.map { it.toArticle() }

}