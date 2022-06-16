package ir.kaaveh.baadbaadaknews.data.mapper

import ir.kaaveh.baadbaadaknews.data.local.FavoriteNewsDto
import ir.kaaveh.baadbaadaknews.domain.model.Article

fun FavoriteNewsDto.toArticle(): Article = Article(
    author = author,
    description = description,
    publishedAt = publishedAt,
    source = source,
    title = title,
    url = url,
    urlToImage = urlToImage,
    isFavorite = true,
)

fun Article.toFavoriteNewsDto(): FavoriteNewsDto = FavoriteNewsDto(
    title = title,
    author = author,
    description = description,
    publishedAt = publishedAt,
    source = source,
    url = url,
    urlToImage = urlToImage,
)