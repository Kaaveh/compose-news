package ir.kaaveh.baadbaadaknews.presentation.bottombar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigateTo
import ir.kaaveh.baadbaadaknews.presentation.NavGraphs
import ir.kaaveh.baadbaadaknews.presentation.appCurrentDestinationAsState
import ir.kaaveh.baadbaadaknews.presentation.destinations.Destination
import ir.kaaveh.baadbaadaknews.presentation.startAppDestination

@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    BottomNavigation {
        BottomBarDestination.values().forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigateTo(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = stringResource(id = destination.label)
                    )
                },
                label = { Text(text = stringResource(id = destination.label)) }
            )
        }
    }
}