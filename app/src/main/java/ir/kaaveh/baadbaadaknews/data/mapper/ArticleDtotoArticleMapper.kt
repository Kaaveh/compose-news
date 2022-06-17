package ir.kaaveh.baadbaadaknews.data.mapper

import ir.kaaveh.baadbaadaknews.data.remote.dto.ArticleDto
import ir.kaaveh.baadbaadaknews.domain.model.Article

fun ArticleDto.toArticle(): Article = Article(
    author = author ?: "",
    description = description ?: "",
    publishedAt = publishedAt ?: "",
    source = source?.name ?: "",
    title = title ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
)