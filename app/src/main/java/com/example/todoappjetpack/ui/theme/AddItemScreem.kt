package com.example.todoappjetpack.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoappjetpack.*
import com.example.todoappjetpack.viewmodel.AddItemFragmentViewModal


@Composable
fun AddItemScreen(viewModel: AddItemFragmentViewModal, backHome: () -> Unit) {
    titleItem = ""
    descriptionItem =""
    statusItem =""
    completedDateItem =""
    createdDateItem =""


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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {

        ClearButton()
        AddButton(viewModel,
            backHome = {
                backHome()
            }
        )

    }
}