package ir.kaaveh.baadbaadaknews.domain.usecase

import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.NewsRepository
import javax.inject.Inject

class RemoveFavoriteNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    suspend operator fun invoke(article: Article) =
        repository.removeFavoriteNews(article)

}