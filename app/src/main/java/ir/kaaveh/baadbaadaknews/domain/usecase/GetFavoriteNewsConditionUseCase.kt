package ir.kaaveh.baadbaadaknews.domain.usecase

import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.JsonNewsRepository
import javax.inject.Inject

class GetFavoriteNewsConditionUseCase @Inject constructor(
    private val repository: JsonNewsRepository,
) {

    suspend operator fun invoke(news: Article): Boolean = repository.isFavoriteNews(news = news)

}