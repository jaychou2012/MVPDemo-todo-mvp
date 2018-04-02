package com.mvp.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.mvpdemo.R;
import com.mvp.mvpdemo.adapter.TaskAdapter;
import com.mvp.mvpdemo.base.BaseFragment;
import com.mvp.mvpdemo.contract.TasksContract;
import com.mvp.mvpdemo.model.Task;
import com.mvp.mvpdemo.utils.OnItemCallBack;
import com.mvp.mvpdemo.utils.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tandong on 2018/4/2.
 * Fragment实现View的接口方法回调
 */

public class TaskFragment extends BaseFragment implements TasksContract.View, OnItemCallBack {
    @Bind(R.id.rv)
    RecyclerView recyclerView;
    @Bind(R.id.tv_tips)
    TextView tv_tips;
    private TaskAdapter taskAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TasksContract.Presenter presenter;

    public TaskFragment() {
        // Requires empty public constructor
    }

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        taskAdapter = new TaskAdapter(getActivity(), Utils.getTasksFromSp());
        taskAdapter.setOnItemCallBack(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.presenter = Utils.checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean show) {
        if (show) {
            tv_tips.setVisibility(View.VISIBLE);
            tv_tips.setText("加载中...");
        } else {
            tv_tips.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTasks(List<Task> tasks) {
        //view的操作具体逻辑实现
        taskAdapter.setDatas(tasks);
    }

    @Override
    public void showAddTaskOk(Task task) {
        //view的操作具体逻辑实现
        showToast("添加任务成功~");
        taskAdapter.addData(task);
    }

    @Override
    public void showTaskDetailsUi(String taskId) {
        //view的操作具体逻辑实现
    }

    @Override
    public void showLoadingTasksError() {
        //view的操作具体逻辑实现
        tv_tips.setVisibility(View.VISIBLE);
        tv_tips.setText("加载失败~");
    }

    @Override
    public void showNoTasks() {
        //view的操作具体逻辑实现
        tv_tips.setVisibility(View.VISIBLE);
        tv_tips.setText("没有数据~");
    }

    @Override
    public boolean isActive() {
        //view的操作具体逻辑实现
        return isAdded();
    }

    @Override
    public void showClearedAllTaskOk() {
        //view的操作具体逻辑实现
        tv_tips.setVisibility(View.VISIBLE);
        tv_tips.setText("没有数据~");
    }

    @Override
    public void showClearedTaskOk(Task task) {
        taskAdapter.removeData(task);
    }

    @Override
    public void onItemLongClick(View v, int posiotion) {
        showToast("长按移除  " + posiotion);
        presenter.clearTask(taskAdapter.getTasks().get(posiotion));
    }

    @Override
    public void onItemClick(View v, int position) {
        Task task = taskAdapter.getTasks().get(position);
        showToast(position + "  " + task.getTitle() + "  " + task.getContent());
    }
}
