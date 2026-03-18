package com.aulasandroid.navegacaofluxotelas

import android.R.attr.type
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aulasandroid.navegacaofluxotelas.ui.theme.NavegacaoFluxoTelasTheme
import com.aulasandroid.navegacaofluxotelas.screens.LoginScreen
import com.aulasandroid.navegacaofluxotelas.screens.MenuScreen
import com.aulasandroid.navegacaofluxotelas.screens.PedidosScreen
import com.aulasandroid.navegacaofluxotelas.screens.PerfilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope
                                    .SlideDirection.Left,
                                animationSpec = tween (2000)
                            ) + fadeOut(animationSpec = tween (2000))
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope
                                    .SlideDirection.Left,
                                animationSpec = tween(1000)
                            )
                        }
                    ) {
                        composable(route = "login") { LoginScreen(
                            navController = navController
                        ) }

                        composable(route = "menu") { MenuScreen(
                            navController = navController
                        ) }

                        composable(
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument("nome") {
                                    type = NavType.StringType
                                },
                                    navArgument("idade") {
                                        type = NavType.IntType
                                    }
                            )
                        ) { navBackStackEntry ->
                            val nome = navBackStackEntry
                                .arguments?.getString("nome")
                            val idade = navBackStackEntry
                                .arguments?.getInt("idade")
                            PerfilScreen(
                                navController = navController,
                                nome = nome!!,
                                idade = idade!!
                            )
                        }

                        composable(route = "pedidos?numeroPedidos={numeroPedido}",
                            arguments = listOf(
                                navArgument("numeroPedido"){
                                    defaultValue = "Sem pedidos"
                                }
                            )
                        ) {
                            val numeroPedido = it
                                .arguments?.getString("numeroPedido")

                            PedidosScreen(
                                navController = navController,
                                numeroPedido = numeroPedido!!
                        ) }
                    }
                }
            }
        }
    }
}
