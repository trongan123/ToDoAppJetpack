package com.example.todoappjetpack

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoappjetpack.model.TodoItem
import com.example.todoappjetpack.ui.theme.*
import com.example.todoappjetpack.viewmodel.AddItemFragmentViewModal
import com.example.todoappjetpack.viewmodel.TodoItemViewModel
import com.example.todoappjetpack.viewmodel.UpdateItemFragmentViewModel

class MainJetpackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var viewModel: TodoItemViewModel =
            ViewModelProvider(this).get(TodoItemViewModel::class.java)
        var addviewModel: AddItemFragmentViewModal =
            ViewModelProvider(this).get(AddItemFragmentViewModal::class.java)
        var updateviewModel: UpdateItemFragmentViewModel =
            ViewModelProvider(this).get(UpdateItemFragmentViewModel::class.java)

//        setContent {
//        //    MainApp(viewModel.getlist(), viewModel, addviewModel,updateviewModel)
//            Log.e("time", "time: "+System.currentTimeMillis() )
//                            LazyColumn() {
//                                items(viewModel.getlist()) { i ->
//                                    ItemList(
//                                        i,
//                                        openUpdateItemScreen = {
//                                            openUpdateItemScreen()
//                                        }, viewModel
//                                    )
//                                }
//                            }
//        }

        viewModel.getStringMutableLiveData()
            .observe(this) { s: String ->
                viewModel.getAllList(viewModel.getStringMutableLiveData().value)
                    .observe(this) { item: List<TodoItem> ->
                        setContent {
                            MainApp(item, viewModel, addviewModel, updateviewModel)
//                            LazyColumn() {
//                                items(listC) { i ->
//                                    ItemList(
//                                        i,
//                                        openUpdateItemScreen = {
//                                            openUpdateItemScreen()
//                                        }, viewModel
//                                    )
//                                }
                        }
                    }
            }

    }
}


@Composable
fun MainApp(
    list: List<TodoItem>,
    viewModel: TodoItemViewModel,
    addviewModel: AddItemFragmentViewModal,
    updateviewModel: UpdateItemFragmentViewModel
) {
    val navController = rememberNavController()
    MaterialTheme() {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    openAddItemScreen = {
                        navController.navigate("additem")
                    },
                    openUpdateItemScreen = {
                        navController.navigate("updateitem")
                    },
                    list = list,
                    viewModel = viewModel
                )
            }
            composable("additem") {
                AddItemScreen(viewModel = addviewModel,
                    backHome = {
                        navController.popBackStack(
                            route = "home",
                            inclusive = false,
                            saveState = true
                        )
                    }
                )
            }
            composable("updateitem") {
                UpdateItemScreen(viewModel = updateviewModel,
                    backHome = {
                        navController.popBackStack(
                            route = "home",
                            inclusive = false,
                            saveState = true
                        )
                    }
                )
            }

        }
    }
}




