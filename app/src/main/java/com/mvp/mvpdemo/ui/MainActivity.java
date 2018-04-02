package com.mvp.mvpdemo.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.mvp.mvpdemo.R;
import com.mvp.mvpdemo.base.BaseActivity;
import com.mvp.mvpdemo.model.Task;
import com.mvp.mvpdemo.model.TaskDaoImpl;
import com.mvp.mvpdemo.presenter.TasksPresenter;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Tandong on 2018/4/2.
 * <p>
 * 这个是根据官方的todo-mvp进行梳理编写的，由于官方的demo不太方便阅读理解，特此改写
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    private TaskFragment taskFragment;
    private TasksPresenter tasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        taskFragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        tasksPresenter = new TasksPresenter(TaskDaoImpl.getInstance(), taskFragment);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setCompleted(false);
        task.setTitle("标题" + System.currentTimeMillis());
        task.setContent("内容" + System.currentTimeMillis());
        tasksPresenter.addNewTask(task);
    }
}
