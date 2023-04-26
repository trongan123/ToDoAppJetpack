package com.example.todoappjetpack

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import com.example.todoappjetpack.model.TodoItem

import com.example.todoappjetpack.ui.theme.*

typealias ComposableFun = @Composable () -> Unit

//sealed class TabItem( var title: String, var screen: ComposableFun ) {
//    object All : TabItem( "All", { AllItemScreen() })
//    object Pending : TabItem( "Pending", { PendingItemScreen() })
//    object Completed : TabItem("Completed", { CompletedItemScreen() })
//}
class TabItem{

    var title: String =""
    var screen: ComposableFun

    constructor(title: String, screen: ComposableFun) {
        this.title = title
        this.screen = screen
    }

}