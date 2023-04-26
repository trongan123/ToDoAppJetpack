package com.example.todoappjetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoappjetpack.ui.theme.HomeScreen
import com.google.accompanist.pager.*

class MainActivity2 : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Home Screen")
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    val intent = Intent(this@MainActivity2, MainActivity::class.java)
                    startActivity(intent)
                }) {
                    Text("Material")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    val intent = Intent(this@MainActivity2, MainJetpackActivity::class.java)
                    startActivity(intent)
                }) {
                    Text(text = "Jetpack")
                }

            }
        }
    }
}



