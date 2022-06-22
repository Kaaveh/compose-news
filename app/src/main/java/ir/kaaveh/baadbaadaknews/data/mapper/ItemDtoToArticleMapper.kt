package ir.kaaveh.baadbaadaknews.data.mapper

import ir.kaaveh.baadbaadaknews.data.remote.khabaronline.dto.ItemDto
import ir.kaaveh.baadbaadaknews.domain.model.Article

fun ItemDto.toArticle(): Article = Article(
    author = author ?: "",
    description = description ?: "",
    publishedAt = pubDate ?: "",
    source = "",
    title = title ?: "",
    url = link ?: "",
    urlToImage = imageNews?.url ?: "",
    isFavorite = false,
)