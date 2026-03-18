package com.aulasandroid.navegacaofluxotelas

import android.os.Bundle
import android.view.Menu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aulasandroid.navegacaofluxotelas.ui.theme.NavegacaoFluxoTelasTheme
import screens.LoginScreen
import screens.MenuScreen
import screens.PedidosScreen
import screens.PerfilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoFluxoTelasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = ""
                    ) {
                        composable(route = "login") { LoginScreen() }

                        composable(route = "menu") { MenuScreen() }

                        composable(route = "perfil") { PerfilScreen() }

                        composable(route = "pedidos") { PedidosScreen() }
                    }
                }
            }
        }
    }
}
