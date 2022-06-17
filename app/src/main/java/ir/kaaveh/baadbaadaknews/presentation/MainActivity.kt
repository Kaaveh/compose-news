package ir.kaaveh.baadbaadaknews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import ir.kaaveh.baadbaadaknews.presentation.bottombar.BottomBar
import ir.kaaveh.baadbaadaknews.presentation.ui.theme.BaadBaadakNewsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaadBaadakNewsTheme {

                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController)
                    }
                ) { paddingValues ->
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = NavGraphs.root,
                        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                    )
                }

            }
        }
    }
}