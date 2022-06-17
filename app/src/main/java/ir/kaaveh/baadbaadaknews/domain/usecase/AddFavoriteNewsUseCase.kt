package ir.kaaveh.baadbaadaknews.domain.usecase

import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.JsonNewsRepository
import javax.inject.Inject

class AddFavoriteNewsUseCase @Inject constructor(
    private val repository: JsonNewsRepository,
) {

    suspend operator fun invoke(article: Article) =
        repository.insertFavoriteNews(article)

}