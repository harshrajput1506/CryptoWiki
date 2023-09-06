package `in`.hypernation.cryptowiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import `in`.hypernation.cryptowiki.screens.Screen
import `in`.hypernation.cryptowiki.screens.detail_screen.DetailScreen
import `in`.hypernation.cryptowiki.screens.home_screen.HomeScreen
import `in`.hypernation.cryptowiki.ui.theme.CryptoWikiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoWikiTheme (dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface( color = MaterialTheme.colorScheme.background ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(
                            route = Screen.HomeScreen.route
                        ){
                            HomeScreen(navController)
                        }

                        composable(
                            route = Screen.DetailScreen.route + "/{coinId}"
                        ) {
                            DetailScreen(navController)
                        }
                    }

                }
            }
        }
    }
}
