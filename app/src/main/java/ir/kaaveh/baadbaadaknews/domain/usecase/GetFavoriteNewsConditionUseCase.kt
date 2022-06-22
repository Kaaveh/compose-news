package ir.kaaveh.baadbaadaknews.domain.usecase

import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.NewsRepository
import javax.inject.Inject

class GetFavoriteNewsConditionUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    suspend operator fun invoke(news: Article): Boolean = repository.isFavoriteNews(news = news)

}