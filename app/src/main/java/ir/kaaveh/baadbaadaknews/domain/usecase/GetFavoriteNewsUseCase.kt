package ir.kaaveh.baadbaadaknews.domain.usecase

import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    operator fun invoke(): Flow<List<Article>> = repository.getAllFavoriteNews()

}