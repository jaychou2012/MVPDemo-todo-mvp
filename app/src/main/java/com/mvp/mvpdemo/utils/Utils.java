package com.mvp.mvpdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvp.mvpdemo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tandong on 2018/4/2.
 */

public class Utils {
    private static SharedPreferences sp;
    private static String KEY = "tasks";

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }

    public static void getInstanceSp(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("mvpdemo", Context.MODE_PRIVATE);
        }
    }

    public static void addTaskToSp(Task task) {
        List<Task> taskList = getTasksFromSp();
        taskList.add(task);
        sp.edit().putString(KEY, new Gson().toJson(taskList)).commit();
    }

    public static List<Task> getTasksFromSp() {
        String tasks = sp.getString(KEY, "");
        if (tasks.trim().length() == 0) {
            return new ArrayList<>();
        }
        List<Task> taskList = new Gson().fromJson(tasks, new TypeToken<List<Task>>() {
        }.getType());
        return taskList;
    }

    public static void deleteTaskToSp(Task task) {
        List<Task> taskList = getTasksFromSp();
        if (taskList.size() > 0) {
            taskList.remove(task);
            sp.edit().putString(KEY, new Gson().toJson(taskList)).commit();
        }
    }

    public static void deleteTaskToSp(String taskId) {
        List<Task> taskList = getTasksFromSp();
        if (taskList.size() > 0) {
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getId().equals(taskId)) {
                    taskList.remove(i);
                }
            }
        }
        sp.edit().putString(KEY, new Gson().toJson(taskList)).commit();
    }

    public static void deleteAllTaskToSp() {
        sp.edit().putString(KEY, "").commit();
    }
}
