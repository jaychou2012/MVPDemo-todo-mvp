package com.mvp.mvpdemo.contract;

import android.support.annotation.NonNull;

import com.mvp.mvpdemo.base.BasePresenter;
import com.mvp.mvpdemo.base.BaseView;
import com.mvp.mvpdemo.model.Task;

import java.util.List;

/**
 * Created by Tandong on 2018/4/2.
 * 一个连接关联类Contract，把View和Presenter关联起来
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {
        /**
         * 这里定义操作View界面相关的回调方法
         */
        void setLoadingIndicator(boolean show);

        void showTasks(List<Task> tasks);

        void showAddTaskOk(Task task);

        void showTaskDetailsUi(String taskId);

        void showLoadingTasksError();

        void showNoTasks();

        boolean isActive();

        void showClearedAllTaskOk();

        void showClearedTaskOk(Task task);
    }

    interface Presenter extends BasePresenter {
        /**
         * 这里定义操作加载处理数据的相关回调方法
         */

        void loadTasks(boolean showLoading);

        void addNewTask(@NonNull Task task);

        void openTaskDetails(@NonNull Task task);

        void clearAllTasks();

        void clearTask(@NonNull Task task);
    }

}
