package ir.kaaveh.baadbaadaknews.presentation.news_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ir.kaaveh.baadbaadaknews.domain.model.Article

@Preview(showBackground = true)
@Composable
fun NewsListItem(
    news: Article? = null,
    onItemClick: (Article) -> Unit = {},
    onFavoriteClick: (Article) -> Unit = {},
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.End,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = news?.urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .width(160.dp)
                    .height(90.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
            ) {
                Text(
                    text = news?.title ?: "TITLE",
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = news?.description ?: "DESCRIPTIONS",
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = news?.source ?: "SOURCE",
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = news?.publishedAt ?: "PUBLISHED_AT",
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "",
            tint = when (news?.isFavorite ?: false) {
                true -> {
                    Color.Red
                }
                false -> {
                    Color.LightGray
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
    }

}