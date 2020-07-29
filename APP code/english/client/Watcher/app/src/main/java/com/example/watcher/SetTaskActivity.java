package com.example.watcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.fishaq.activity.FishBaseAppCompatActivity;
import com.example.fishaq.annotation.ViewInject;
import com.example.fishaq.internet.FishHttp;
import com.example.watcher.adapter.TaskAdapter;
import com.example.watcher.beans.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SetTaskActivity extends FishBaseAppCompatActivity {

    @ViewInject(R.id.content)
    private EditText content;

    @ViewInject(R.id.start)
    private Button start;

    @ViewInject(R.id.end)
    private Button end;

    @ViewInject(R.id.taskList)
    private ListView taskList;


    private String teaid;
    private String stuid;

    private List<Task> tasks=new ArrayList<>();
    private TaskAdapter taskAdapter;

    @Override
    public int initLayoutId() {
        return R.layout.activity_set_task;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Set task");

        Intent intent=getIntent();
        teaid = intent.getStringExtra("teaid");
        stuid = intent.getStringExtra("stuid");

        getList();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(SetTaskActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

                        start.setText(simpleDateFormat.format(date));
                    }
                }).setType(new boolean[]{true, true, true, true, true, true}).build();
                pvTime.show();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(SetTaskActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

                        end.setText(simpleDateFormat.format(date));
                    }
                }).setType(new boolean[]{true, true, true, true, true, true}).build();
                pvTime.show();
            }
        });

    }

    private void getList() {

        new FishHttp() {
            @Override
            public void OnCallBack(String result) {
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    tasks.clear();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String content = jsonObject.getString("content");
                        String starttime = jsonObject.getString("starttime");
                        String endtime = jsonObject.getString("endtime");
                        String status = jsonObject.getString("status");
                        String id = jsonObject.getString("id");

                        Task task=new Task();
                        task.setContent(content);
                        task.setId(id);
                        task.setStarttime(starttime);
                        task.setEndtime(endtime);
                        task.setStatus(status);

                        tasks.add(task);

                        if(taskAdapter==null){
                            taskAdapter=new TaskAdapter(tasks,SetTaskActivity.this,stuid);
                            taskList.setAdapter(taskAdapter);
                        }
                        taskAdapter.notifyDataSetChanged();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.okGet(NetConfig.gettask+"?stuid="+stuid);

    }

    public void add(View view) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("content",content.getText().toString());
            jsonObject.put("starttime",start.getText().toString());
            jsonObject.put("endtime",end.getText().toString());
            jsonObject.put("stuid",stuid);
            jsonObject.put("status","normal");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new FishHttp() {
            @Override
            public void OnCallBack(String result) {
                Toast.makeText(SetTaskActivity.this, result, Toast.LENGTH_SHORT).show();
                getList();
            }
        }.okPostBody(NetConfig.inserttask,jsonObject);

    }
}
