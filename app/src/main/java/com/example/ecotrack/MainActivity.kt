package com.example.ecotrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecotrack.ui.theme.EcoTrackTheme
import com.example.ecotrack.ui.theme.NavGraph

/**
 * Main Activity - Entry point of the application.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcoTrackTheme {
                NavGraph()
            }
        }
    }
}

/**
 * Greeting composable used for previews/examples.
 */
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/**
 * Preview for the Greeting composable.
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcoTrackTheme {
        Greeting("Android")
    }
}
