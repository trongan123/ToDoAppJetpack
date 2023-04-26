package com.example.todoappjetpack.ui.theme

import android.util.Log
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.todoappjetpack.model.TodoItem
import com.example.todoappjetpack.viewmodel.AddItemFragmentViewModal
import com.example.todoappjetpack.viewmodel.UpdateItemFragmentViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.text.SimpleDateFormat



@Composable
fun test() {
    var showDeleteConfirm by remember { mutableStateOf(false) }
    Button(onClick = { showDeleteConfirm = true }) {
        Text("Delete")
    }
    if (showDeleteConfirm) {
        ConfirmDialog(
            content = "Confirm Delete?",
            onDismiss = { showDeleteConfirm = false },
            onConfirm = {
                showDeleteConfirm = false
                onDelete() // perform actual operation
            }
        )
    }
}

fun onDelete() {

}

@Composable
fun ConfirmDialog(title: String? = null, content: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = {
            onDismiss()
        },

        text = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                // horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // CircularProgressIndicator()
                // Text("Hello")
                if (!title.isNullOrEmpty()) {
                    Text(title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        style = MaterialTheme.typography.titleLarge)
                }
                Text(content)
                // Timber.d("message=${progressState.message}")
            }
        },
        // buttons = { }
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("No")
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text("Yes")
            }
        }
    )
}