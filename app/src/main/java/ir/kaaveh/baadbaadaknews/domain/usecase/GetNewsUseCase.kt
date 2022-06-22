package ir.kaaveh.baadbaadaknews.domain.usecase

import ir.kaaveh.baadbaadaknews.common.Resource
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())

            val orgNewsArticles =
                repository.getOrgNews().map { it.copy(isFavorite = repository.isFavoriteNews(it)) }
            val khabaronlineArticles = repository.getKhabaronlineNews()
                .map { it.copy(isFavorite = repository.isFavoriteNews(it)) }
            val allArticle = orgNewsArticles + khabaronlineArticles

            emit(Resource.Success(allArticle))
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }

}