package com.mvp.mvpdemo.model;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.mvp.mvpdemo.utils.Utils;

import java.util.List;

/**
 * Created by Tandong on 2018/4/2.
 * Task的Model的接口方法的具体实现，采用单例模式
 */

public class TaskDaoImpl implements TaskDao {
    private static TaskDaoImpl INSTANCE = null;
    private static final int SERVICE_LATENCY_IN_MILLIS = 500;
    private static List<Task> TASKS_SERVICE_DATA = null;

    // 防止直接实例化
    private TaskDaoImpl() {
    }

    /**
     * 单例模式创建TaskDaoImpl
     *
     * @return
     */
    public static TaskDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskDaoImpl();
        }
        return INSTANCE;
    }

    /**
     * 销毁回收实例对象
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        //逻辑的具体实现，这里获取数据
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TASKS_SERVICE_DATA = Utils.getTasksFromSp();
                callback.onTasksLoaded(TASKS_SERVICE_DATA);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull final GetTaskCallback callback) {
        int position = 0;
        TASKS_SERVICE_DATA = Utils.getTasksFromSp();
        for (int i = 0; i < TASKS_SERVICE_DATA.size(); i++) {
            if (TASKS_SERVICE_DATA.get(i).getId().equals(taskId)) {
                position = i;
            }
        }
        //逻辑的具体实现
        final Task task = TASKS_SERVICE_DATA.get(position);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTaskLoaded(task);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void saveTask(@NonNull Task task) {
        //逻辑的具体实现
        TASKS_SERVICE_DATA.add(task);
        Utils.addTaskToSp(task);
    }

    @Override
    public void refreshTasks() {
        //逻辑的具体实现
        TASKS_SERVICE_DATA = Utils.getTasksFromSp();
    }

    @Override
    public void deleteAllTasks() {
        //逻辑的具体实现
        TASKS_SERVICE_DATA.clear();
        Utils.deleteAllTaskToSp();
    }

    @Override
    public void deleteTask(@NonNull Task task) {
        //逻辑的具体实现
        Utils.deleteTaskToSp(task);
    }
}
