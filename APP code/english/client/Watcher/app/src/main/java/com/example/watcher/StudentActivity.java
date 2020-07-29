package com.example.watcher;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fishaq.activity.FishBaseAppCompatActivity;
import com.example.fishaq.annotation.ViewInject;
import com.example.fishaq.internet.FishHttp;
import com.example.watcher.adapter.StudentAdapter;
import com.example.watcher.beans.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentActivity extends FishBaseAppCompatActivity {

    private List<Task> tasks=new ArrayList<>();
    private StudentAdapter studentAdapter;

    @ViewInject(R.id.taskList)
    private ListView taskList;

    @Override
    public int initLayoutId() {
        return R.layout.activity_student;
    }


    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }


    @Override
    protected void onPause() {
        super.onPause();

        String in = getIntent().getStringExtra("in");
        String stuid="";
        try {
            JSONObject inJson=new JSONObject(in);
            stuid=inJson.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String fStuid=stuid;
        new FishHttp() {
            @Override
            public void OnCallBack(String result) {


                try {
                    JSONArray jsonArray=new JSONArray(result);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String content = jsonObject.getString("content");
                        String starttime = jsonObject.getString("starttime");
                        String endtime = jsonObject.getString("endtime");
                        String status = jsonObject.getString("status");
                        String id = jsonObject.getString("id");


                        Date date=new Date();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date start = simpleDateFormat.parse(starttime);
                            Date end = simpleDateFormat.parse(endtime);
//                            if(start.getTime()<date.getTime()&&end.getTime()>date.getTime()){

                                Bundle bundle=new Bundle();
                                bundle.putString("stuid",fStuid);
                                new FishHttp() {
                                    @Override
                                    public void OnCallBack(String result) {
                                        //Toast.makeText(StudentActivity.this, result, Toast.LENGTH_SHORT).show();
                                    }
                                }.okPost(NetConfig.leave,bundle);

//                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.okGet(NetConfig.gettask+"?stuid="+stuid);
    }

    private void getList() {

        String in = getIntent().getStringExtra("in");
        String id="";

        try {
            JSONObject inJson=new JSONObject(in);
            id=inJson.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String fId=id;

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

                        if(studentAdapter==null){
                            studentAdapter=new StudentAdapter(tasks,StudentActivity.this,fId);
                            taskList.setAdapter(studentAdapter);
                        }
                        studentAdapter.notifyDataSetChanged();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.okGet(NetConfig.gettask+"?stuid="+id);

    }
}
