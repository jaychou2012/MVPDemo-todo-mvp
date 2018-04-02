package com.mvp.mvpdemo.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Tandong on 2018/4/2.
 * 对应的Task的Model的主要方法接口定义类
 */

public interface TaskDao {
    void getTasks(@NonNull LoadTasksCallback callback);

    void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(@NonNull Task task);

    interface LoadTasksCallback {

        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }
}
