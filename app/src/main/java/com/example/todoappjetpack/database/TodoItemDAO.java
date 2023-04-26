package com.example.todoappjetpack.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoappjetpack.model.TodoItem;

import java.util.List;

@Dao
public interface TodoItemDAO {
    @Insert
    void insertTodoItem(TodoItem todoItem);

    @Query("SELECT * FROM todoItem WHERE title LIKE '%' || :key || '%' ORDER BY id DESC ")
    LiveData<List<TodoItem>> getlistTodoItem(String key);


    @Query("SELECT * FROM todoItem WHERE status=:status AND title LIKE '%' || :key || '%'")
    LiveData<List<TodoItem>> getlistTodoItemByStatus(String status,String key);

    @Update
    void updateTodoItem(TodoItem item);

    @Delete
    void deleteTodoItem(TodoItem item);


    @Query("delete from todoItem where id in (:id)")
    void clearTodoItem(List<Integer> id);
}
