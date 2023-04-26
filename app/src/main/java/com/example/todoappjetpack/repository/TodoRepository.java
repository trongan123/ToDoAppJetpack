package com.example.todoappjetpack.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoappjetpack.database.TodoItemDAO;
import com.example.todoappjetpack.database.TodoItemDatabase;
import com.example.todoappjetpack.model.TodoItem;

import java.util.List;

public class TodoRepository {

    private TodoItemDAO mTodoItemDAO;
    private LiveData<List<TodoItem>> mTodoItems;

    public TodoRepository(Application application) {
        TodoItemDatabase db = TodoItemDatabase.getInstance(application);
        mTodoItemDAO = db.todoItemDAO();
    }


    public LiveData<List<TodoItem>> getAllList(String key) {
        mTodoItems = mTodoItemDAO.getlistTodoItem(key);
        return mTodoItems;
    }
    public LiveData<List<TodoItem>> getlistTodoItemByStatus(String status, String key) {
        mTodoItems = mTodoItemDAO.getlistTodoItemByStatus(status,key);
        return mTodoItems;
    }



    public  void insert(TodoItem todoItem) {
        mTodoItemDAO.insertTodoItem(todoItem);
    }
    public  void update(TodoItem todoItem) {
        mTodoItemDAO.updateTodoItem(todoItem);
    }
    public void delete(TodoItem todoItem) {
        mTodoItemDAO.deleteTodoItem(todoItem);
    }
    public void clear(List<Integer> id) {
        mTodoItemDAO.clearTodoItem(id);
    }
}


