package com.example.watcher;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.fishaq.activity.FishBaseAppCompatActivity;
import com.example.fishaq.annotation.ViewInject;
import com.example.fishaq.internet.FishHttp;
import com.example.watcher.adapter.TeacherAdapter;
import com.example.watcher.beans.Tea;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class TeacherActivity extends FishBaseAppCompatActivity {

    @ViewInject(R.id.studentList)
    private ListView studentList;

    private TeacherAdapter teacherAdapter;

    private List<Tea> teaList=new ArrayList<>();

    private volatile boolean flag=true;

    @Override
    public int initLayoutId() {
        return R.layout.activity_teacher;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();

        flag=true;

        final Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                initList();
            }
        };

        new Thread(){
            @Override
            public void run() {
                super.run();
                while (flag){
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag=false;
    }

    void initList(){

        String in = getIntent().getStringExtra("in");
        String id="";
        JSONObject jsonObject=new JSONObject();
        try {
            JSONObject inJson=new JSONObject(in);
            id=inJson.getString("id");
            jsonObject.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String fId=id;
        new FishHttp() {
            @Override
            public void OnCallBack(String result) {
                try {
                    JSONArray jsonArray=new JSONArray(result);
                    teaList.clear();
                    for(int i=0;i<jsonArray.length();i++){

                        Tea tea=new Tea();
                        tea.setId(jsonArray.getJSONObject(i).getString("id"));
                        tea.setUsername(jsonArray.getJSONObject(i).getString("username"));
                        tea.setStatus(jsonArray.getJSONObject(i).getString("status"));
                        teaList.add(tea);

                    }
                    if(teacherAdapter==null){
                        teacherAdapter=new TeacherAdapter( TeacherActivity.this,teaList,fId);
                        studentList.setAdapter(teacherAdapter);
                    }

                    teacherAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.okPostBody(NetConfig.getrelations,jsonObject);



    }

    /**
     *创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student,menu); //通过getMenuInflater()方法得到MenuInflater对象，再调用它的inflate()方法就可以给当前活动创建菜单了，第一个参数：用于指定我们通过哪一个资源文件来创建菜单；第二个参数：用于指定我们的菜单项将添加到哪一个Menu对象当中。
        return true; // true：允许创建的菜单显示出来，false：创建的菜单将无法显示。
    }

    /**
     *菜单的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.seltea:
                Intent intent=new Intent(TeacherActivity.this,ChooseActivity.class);
                intent.putExtra("in", getIntent().getStringExtra("in"));
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("View Children");
    }
}
