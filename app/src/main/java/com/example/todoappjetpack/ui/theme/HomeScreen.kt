package com.example.todoappjetpack.ui.theme

import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.text.input.TextFieldValue

import com.example.todoappjetpack.SearchView
import com.example.todoappjetpack.model.TodoItem
import com.example.todoappjetpack.viewmodel.TodoItemViewModel

@Composable
fun HomeScreen(
    openAddItemScreen: () -> Unit,
    openUpdateItemScreen: () -> Unit,
    list: List<TodoItem>,
    viewModel: TodoItemViewModel
) {
        Column() {
            TabScreen(openAddItemScreen ={
                openAddItemScreen()
            },openUpdateItemScreen ={
                openUpdateItemScreen()
            },listAll = list,
                viewModel=viewModel
            )
        }

}