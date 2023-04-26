package com.example.todoappjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.todoappjetpack.model.TodoItem
import com.example.todoappjetpack.viewmodel.TodoItemViewModel

class MainJetpackFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            var viewModel: TodoItemViewModel =
                ViewModelProvider(requireActivity()).get(TodoItemViewModel::class.java)

            viewModel.getStringMutableLiveData()
                .observe(requireActivity()) { s: String ->
                    viewModel.getAllList(viewModel.getStringMutableLiveData().value)
                        .observe(requireActivity()) { item: List<TodoItem> ->
                            setContent {

                            }
                        }
                }

        }
    }
}