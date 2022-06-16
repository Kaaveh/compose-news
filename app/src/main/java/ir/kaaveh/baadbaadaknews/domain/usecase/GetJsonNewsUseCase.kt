package ir.kaaveh.baadbaadaknews.domain.usecase

import ir.kaaveh.baadbaadaknews.common.Resource
import ir.kaaveh.baadbaadaknews.domain.model.Article
import ir.kaaveh.baadbaadaknews.domain.repository.JsonNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetJsonNewsUseCase @Inject constructor(
    private val repository: JsonNewsRepository,
) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())
            val articles =
                repository.getJsonNews().map { it.copy(isFavorite = repository.isFavoriteNews(it)) }
            emit(Resource.Success(articles))
        } catch (e: HttpException) {
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(e))
        }
    }

}