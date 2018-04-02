package com.mvp.mvpdemo.presenter;

import android.support.annotation.NonNull;

import com.mvp.mvpdemo.contract.TasksContract;
import com.mvp.mvpdemo.model.Task;
import com.mvp.mvpdemo.model.TaskDao;
import com.mvp.mvpdemo.model.TaskDaoImpl;
import com.mvp.mvpdemo.utils.Utils;

import java.util.List;

/**
 * Created by Tandong on 2018/4/2.
 * Presenter的业务真正实现，监听用户UI操作，回调和更新数据给UI
 */

public class TasksPresenter implements TasksContract.Presenter {
    private final TaskDaoImpl taskDaoImpl;
    private final TasksContract.View tasksView;

    public TasksPresenter(@NonNull TaskDaoImpl taskDaoImpl, @NonNull TasksContract.View tasksView) {
        this.taskDaoImpl = Utils.checkNotNull(taskDaoImpl, "tasksRepository cannot be null");
        this.tasksView = Utils.checkNotNull(tasksView, "tasksView cannot be null!");

        this.tasksView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks(true);
    }

    @Override
    public void loadTasks(boolean showLoading) {
        toLoadTasks(showLoading);
    }

    /**
     * 添加数据操作
     */
    @Override
    public void addNewTask(@NonNull Task task) {
        //操作Model
        taskDaoImpl.saveTask(task);
        //操作View
        tasksView.showAddTaskOk(task);
    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {
        Utils.checkNotNull(requestedTask, "requestedTask cannot be null!");
        tasksView.showTaskDetailsUi(requestedTask.getId());
    }

    /**
     * 删除全部数据操作
     */
    @Override
    public void clearAllTasks() {
        //操作Model
        taskDaoImpl.deleteAllTasks();
        //操作View
        tasksView.showClearedAllTaskOk();
    }

    /**
     * 删除单条数据操作
     *
     * @param task
     */
    @Override
    public void clearTask(@NonNull Task task) {
        //操作Model
        taskDaoImpl.deleteTask(task);
        //操作View
        tasksView.showClearedTaskOk(task);
    }

    private void toLoadTasks(final boolean showLoadingUI) {
        if (showLoadingUI) {//显示加载中进度条布局
            tasksView.setLoadingIndicator(true);
        }
        taskDaoImpl.getTasks(new TaskDao.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                // The view may not be able to handle UI updates anymore
                if (!tasksView.isActive()) {
                    return;
                }
                if (showLoadingUI) {//隐藏加载中进度条
                    tasksView.setLoadingIndicator(false);
                }
                processTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                // The view may not be able to handle UI updates anymore
                if (!tasksView.isActive()) {
                    return;
                }
                tasksView.showLoadingTasksError();
            }
        });
    }

    private void processTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            processEmptyTasks();
        } else {
            //加载完毕后回调给View进行显示逻辑处理
            tasksView.showTasks(tasks);
        }
    }

    private void processEmptyTasks() {
        tasksView.showNoTasks();
    }

}
