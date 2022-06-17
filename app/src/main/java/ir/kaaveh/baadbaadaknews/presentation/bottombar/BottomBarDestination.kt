package ir.kaaveh.baadbaadaknews.presentation.bottombar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import ir.kaaveh.baadbaadaknews.R
import ir.kaaveh.baadbaadaknews.presentation.destinations.FavoriteNewsScreenDestination
import ir.kaaveh.baadbaadaknews.presentation.destinations.NewsListScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int,
) {
    News(NewsListScreenDestination, Icons.Default.Home, R.string.home),
    FavoriteNews(FavoriteNewsScreenDestination, Icons.Default.Favorite, R.string.favorites),
}