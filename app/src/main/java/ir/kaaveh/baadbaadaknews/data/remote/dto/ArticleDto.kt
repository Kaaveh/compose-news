package ir.kaaveh.baadbaadaknews.data.remote.dto

import ir.kaaveh.baadbaadaknews.domain.model.Article

data class ArticleDto(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String,
)

fun ArticleDto.toArticle(): Article = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.name,
    title = title,
    url = url,
    urlToImage = urlToImage,
)