package com.example.todoappjetpack.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.todoappjetpack.model.TodoItem;
import com.example.todoappjetpack.repository.TodoRepository;

public class UpdateItemFragmentViewModel extends AndroidViewModel {
    private final TodoRepository mRepository;
    private TodoItem todoItem;
    public UpdateItemFragmentViewModel(Application application) {
        super(application);
        mRepository = new TodoRepository(application);

    }
    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

    public void updateItem(TodoItem todoItem){
        mRepository.update(todoItem);
    }

    public void deleteItem(TodoItem todoItem){
        mRepository.delete(todoItem);
    }
}
