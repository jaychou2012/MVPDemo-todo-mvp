package com.mvp.mvpdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.mvpdemo.R;
import com.mvp.mvpdemo.model.Task;
import com.mvp.mvpdemo.utils.OnItemCallBack;

import java.util.List;

/**
 * Created by Tandong on 2018/3/28.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private Context context;
    private List<Task> tasks;
    private OnItemCallBack onItemCallBack;

    public TaskAdapter(Context context, List<Task> list) {
        this.context = context;
        this.tasks = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(
                R.layout.item_task, null);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(tasks.get(position).getTitle());
        holder.tv_content.setText(tasks.get(position).getContent());
        holder.itemView.setOnClickListener(new MyClick(position));
        holder.itemView.setOnLongClickListener(new MyLongClick(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_content;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    private class MyClick implements View.OnClickListener {

        int position;

        public MyClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (onItemCallBack != null) {
                onItemCallBack.onItemClick(v, position);
            }
        }
    }

    private class MyLongClick implements View.OnLongClickListener {

        int position;

        public MyLongClick(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemCallBack != null) {
                onItemCallBack.onItemLongClick(v, position);
            }
            return true;
        }
    }

    public void setOnItemCallBack(OnItemCallBack onItemCallBack) {
        this.onItemCallBack = onItemCallBack;
    }

    public void setDatas(List<Task> list) {
        tasks.clear();
        tasks.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(Task task) {
        tasks.add(task);
        notifyDataSetChanged();
    }

    public void removeData(Task task) {
        tasks.remove(task);
        notifyDataSetChanged();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
