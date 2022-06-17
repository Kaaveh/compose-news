package ir.kaaveh.baadbaadaknews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import ir.kaaveh.baadbaadaknews.presentation.ui.theme.BaadBaadakNewsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaadBaadakNewsTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}