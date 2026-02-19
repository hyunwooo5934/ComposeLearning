package com.example.composeex

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.example.composeex.core.ui.theme.ComposeEXTheme
import com.example.composeex.feature.main.navigation.MainRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private lateinit var navHostController: NavHostController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            ComposeEXTheme {
                MainRoute()
            }
        }
    }
}
