package com.example.watcher.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fishaq.internet.FishHttp;
import com.example.watcher.NetConfig;
import com.example.watcher.R;
import com.example.watcher.beans.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private List<Task> tasks;
    private Context context;
    private String stuid;

    public StudentAdapter(List<Task> tasks, Context context, String stuid) {
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
        View view1=View.inflate(context, R.layout.item_student,null);
        TextView content = view1.findViewById(R.id.content);
        TextView time = view1.findViewById(R.id.time);

        content.setText(tasks.get(i).getContent());
        time.setText(tasks.get(i).getStarttime()+"至"+tasks.get(i).getEndtime());

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = simpleDateFormat.parse(tasks.get(i).getStarttime());
            Date end = simpleDateFormat.parse(tasks.get(i).getEndtime());
            if(start.getTime()<date.getTime()&&end.getTime()>date.getTime()){
                //在任务时间内
                Bundle bundle=new Bundle();
                bundle.putString("stuid",stuid);
                new FishHttp() {
                    @Override
                    public void OnCallBack(String result) {
                        //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    }
                }.okPost(NetConfig.back,bundle);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return view1;
    }
}
