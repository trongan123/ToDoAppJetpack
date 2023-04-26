package com.example.todoappjetpack.ui.theme

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todoappjetpack.*
import com.example.todoappjetpack.R
import com.example.todoappjetpack.model.TodoItem
import com.example.todoappjetpack.viewmodel.TodoItemViewModel
import com.google.accompanist.pager.*

import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(
    openAddItemScreen: () -> Unit,
    openUpdateItemScreen: () -> Unit,
    listAll: List<TodoItem>,
    viewModel: TodoItemViewModel
) {

    val tabs: MutableList<TabItem> = ArrayList<TabItem>().toMutableList()
    tabs += TabItem("All") {
        AllItemScreen(
            listAll, openUpdateItemScreen = {
                openUpdateItemScreen()
            }, viewModel = viewModel
        )
    }
    tabs += TabItem("Pending") {
        PendingItemScreen(listAll.filter { it.status.equals("pending") }, openUpdateItemScreen = {
            openUpdateItemScreen()
        }, viewModel = viewModel)
    }
    tabs += TabItem("Completed") {
        CompletedItemScreen(
            listAll.filter { it.status.equals("completed") },
            openUpdateItemScreen = {
                openUpdateItemScreen()
            }, viewModel = viewModel
        )
    }
    val pagerState = rememberPagerState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openAddItemScreen() }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) { padding ->
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            SearchView(state = textState, viewModel)
            Spacer(modifier = Modifier.size(16.dp))
            Tabs(
                tabs = tabs, pagerState = pagerState,
                viewModel = viewModel
            )
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(
    tabs: List<TabItem>,
    pagerState: PagerState,
    viewModel: TodoItemViewModel
) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    Row {
        ScrollableTabRow(
            modifier = Modifier.weight(1f),
            edgePadding = 0.dp,
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            backgroundColor = Color.White,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            // Add tabs for all of our pages
            tabs.forEachIndexed { index, tab ->
                // OR Tab()
                LeadingIconTab(
                    icon = { },
                    text = { Text(tab.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }

        }
        var showDeleteConfirm by remember { mutableStateOf(false) }
        Button(
            onClick = {
                showDeleteConfirm = true
            },
            shape = RoundedCornerShape(20.dp)

        ) {
            androidx.compose.material.Text("ClearAll")
        }

        if (showDeleteConfirm) {
            ConfirmDialog(
                content = "Confirm Clear All?",
                onDismiss = { showDeleteConfirm = false },
                onConfirm = {
                    showDeleteConfirm = false
                    viewModel.clearItem()
                }
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}


@Composable
fun AllItemScreen(
    listAll: List<TodoItem>,
    openUpdateItemScreen: () -> Unit,
    viewModel: TodoItemViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        LazyColumn() {

            items(listAll) { i ->
                ItemList(
                    i,
                    openUpdateItemScreen = {
                        openUpdateItemScreen()
                    }, viewModel
                )
            }
        }
    }
}


@Composable
fun PendingItemScreen(
    listP: List<TodoItem>,
    openUpdateItemScreen: () -> Unit,
    viewModel: TodoItemViewModel
) {
//    var listPend by remember { mutableStateOf(listPending) }
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        LazyColumn() {
            items(listP,
//                key = {
//                    it.id }
            ) { i ->
                ItemList(
                    i,
                    openUpdateItemScreen = {
                        openUpdateItemScreen()
                    }, viewModel
                )
            }
        }
    }
}


@Composable
fun CompletedItemScreen(
    listC: List<TodoItem>,
    openUpdateItemScreen: () -> Unit,
    viewModel: TodoItemViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn() {
            items(listC) { i ->
                ItemList(
                    i,
                    openUpdateItemScreen = {
                        openUpdateItemScreen()
                    }, viewModel
                )
            }
        }
    }
}

fun setContent(composeView: ComposeView, composeparam1: Boolean){
    composeView.setContent {
        Log.e("ssadasda", "setContent: "+composeparam1 )
    }
}

@Composable
fun ItemList(
    i: TodoItem, openUpdateItemScreen: () -> Unit,
    viewModel: TodoItemViewModel
) {
    Log.e("time", "time build item: "+System.currentTimeMillis() )
    var time1= System.currentTimeMillis()
    val isChecked = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                if (isChecked.value == true) {
                    isChecked.value = false
                    viewModel.setClearAll(i.id, false)
                    viewModel.setCheckData(i.id, false)
                } else {
                    isChecked.value = true
                    viewModel.setClearAll(i.id, true)
                    viewModel.setCheckData(i.id, true)
                }
            },
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Row {
//                val check: List<Int>? = viewModel.getListMutableCheck().value as List<Int>
//                if (check != null) {
//                    isChecked.value = check.contains(i.id)
//                }
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                        viewModel.setClearAll(i.id, it)
                        viewModel.setCheckData(i.id, it)
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                if (!isChecked.value) {
                    Text(
                        i.title, modifier = Modifier
                            .padding(top = 10.dp)
                    )
                } else Text(
                    i.title,
                    modifier = Modifier
                        .padding(top = 10.dp),
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
                Spacer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Image(painter = painterResource(id = R.drawable.ellipsis),
                    contentDescription = null,

                    modifier = Modifier
                        .clickable {
                            todoItem = i
                            openUpdateItemScreen()
                        }
                        .size(30.dp))
                Spacer(modifier = Modifier.size(10.dp))
            }

        }
    }
    var time2 =System.currentTimeMillis()
    Log.e("time", "time build item end: "+System.currentTimeMillis() )
    Log.e("time", "time "+(time2-time1) )

}