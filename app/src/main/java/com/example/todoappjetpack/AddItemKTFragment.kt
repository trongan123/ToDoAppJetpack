package com.example.todoappjetpack

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController

import com.example.todoappjetpack.model.TodoItem
import com.example.todoappjetpack.viewmodel.AddItemFragmentViewModal
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.text.SimpleDateFormat

class AddItemKTFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var viewModel: AddItemFragmentViewModal =
            ViewModelProvider(requireActivity()).get(AddItemFragmentViewModal::class.java)
        return ComposeView(requireContext()).apply {
            titleItem = ""
            createdDateItem =""
            completedDateItem =""
            statusItem =""
            descriptionItem= ""
            todoItem = TodoItem()
            setContent {
                Column(modifier = Modifier.padding(30.dp)) {
                    Texttitle()
                    CommonSpace()
                    TextDescription()
                    CommonSpace()
                    TextCreatedDate()
                    CommonSpace()
                    TextCompletedDate()
                    CommonSpace()
                    dropDownMenuStatus()

                }

            }

        }
    }
}





