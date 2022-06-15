package ir.kaaveh.baadbaadaknews.domain.repository

import ir.kaaveh.baadbaadaknews.domain.model.Article

interface JsonNewsRepository {

    suspend fun getJsonNews(): List<Article>

}