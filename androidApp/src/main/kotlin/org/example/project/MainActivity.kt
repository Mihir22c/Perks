package org.example.project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }

        val repo = createOffersRepository()
        CoroutineScope(Dispatchers.Main).launch {
            launch { repo.observeOffers().collect { Log.d("PERKS", "DB now has ${it.size}") } }
            repo.refresh()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}