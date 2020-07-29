package com.example.watcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fishaq.activity.FishBaseAppCompatActivity;
import com.example.fishaq.annotation.ViewInject;
import com.example.fishaq.internet.FishHttp;
import com.example.watcher.adapter.ChooseAdapter;
import com.example.watcher.beans.Choose;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends FishBaseAppCompatActivity {

    private ChooseAdapter chooseAdapter;

    private boolean flag=false;

    private String teaid;

    @ViewInject(R.id.teacherList)
    private ListView teacherList;

    @Override
    public int initLayoutId() {
        return R.layout.activity_choose;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent in=getIntent();
        String json = in.getStringExtra("in");
        try {
            JSONObject jsonObject=new JSONObject(json);
            teaid=jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getList();

    }

    private void getList() {
        new FishHttp() {
            @Override
            public void OnCallBack(final String result) {

                new FishHttp() {
                    @Override
                    public void OnCallBack(String result2) {


                        try {
                            JSONArray jsonArray=new JSONArray(result);
                            JSONArray jsonArray2=new JSONArray(result2);
                            List<Choose> list=new ArrayList<>();
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String username = jsonObject.getString("username");
                                String id = jsonObject.getString("id");
                                String status="Not selected";
                                int length = jsonArray2.length();
                                for(int j=0;j<length;j++){
                                    JSONObject jsonObject1 = jsonArray2.getJSONObject(j);
                                    String id1 = jsonObject1.getString("id");
                                    if(id.equals(id1)){
                                        status="Chosen";
                                    }
                                }
                                Choose choose=new Choose();
                                choose.setUsername(username);
                                choose.setStatus(status);
                                choose.setId(id);
                                list.add(choose);
                            }

                            if(!flag){
                                chooseAdapter=new ChooseAdapter(list,ChooseActivity.this,teaid);
                                teacherList.setAdapter(chooseAdapter);
                                flag=true;
                            }
                            chooseAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.okGet(NetConfig.getalllink+"?teaid="+teaid);


            }
        }.okGet(NetConfig.getallstu);
    }
}
