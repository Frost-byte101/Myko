package com.example.watcher.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fishaq.internet.FishHttp;
import com.example.watcher.NetConfig;
import com.example.watcher.R;
import com.example.watcher.beans.Task;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private List<Task> tasks;
    private Context context;
    private String stuid;

    public TaskAdapter(List<Task> tasks, Context context,String stuid) {
        this.tasks = tasks;
        this.context = context;
        this.stuid = stuid;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1=View.inflate(context, R.layout.item_task,null);
        TextView taskContent = view1.findViewById(R.id.task_content);
        TextView taskTime = view1.findViewById(R.id.task_time);
        Button delBtn = view1.findViewById(R.id.del_btn);

        taskContent.setText(tasks.get(i).getContent());
        taskTime.setText(tasks.get(i).getStarttime()+"To"+tasks.get(i).getEndtime());

        final int pos=i;

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Point")
                        .setMessage("Confirm to delete？")
                        .setPositiveButton("determine", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Bundle bundle=new Bundle();
                                bundle.putString("id",tasks.get(pos).getId());
                                new FishHttp() {
                                    @Override
                                    public void OnCallBack(String result) {
                                        if("Successfully deleted".equals(result)){
                                            tasks.remove(pos);
                                            TaskAdapter.this.notifyDataSetChanged();
                                        }
                                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                                    }
                                }.okPost(NetConfig.deltask,bundle);
                            }
                        })
                        .setNegativeButton("cancel",null)
                        .create()
                        .show();
            }
        });

        return view1;
    }
}
