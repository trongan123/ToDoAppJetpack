package com.example.todoappjetpack.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.todoappjetpack.AllItemFragment;
import com.example.todoappjetpack.CompletedItemFragment;
import com.example.todoappjetpack.PendingItemFragment;
import com.example.todoappjetpack.viewmodel.TodoItemViewModel;

public class TabItemAdapter extends FragmentStateAdapter {
    private TodoItemViewModel todoItemViewModel;
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new AllItemFragment(todoItemViewModel);
            case 1 :
                return new PendingItemFragment(todoItemViewModel);
            default:
                return new CompletedItemFragment(todoItemViewModel);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public TabItemAdapter(@NonNull FragmentActivity fragmentActivity, TodoItemViewModel todoItemViewModel) {
        super(fragmentActivity);
        this.todoItemViewModel = todoItemViewModel;
    }
}