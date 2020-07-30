package com.example.watcher;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fishaq.activity.FishBaseAppCompatActivity;
import com.example.fishaq.annotation.ViewInject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends FishBaseAppCompatActivity {

    @ViewInject(R.id.time)
    private TextView time;

    @ViewInject(R.id.add)
    private ImageView add;

    @ViewInject(R.id.tasks)
    private ImageView tasks;

    @Override
    public int initLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Select");

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String format = simpleDateFormat.format(date);
        time.setText(format);

        String in = getIntent().getStringExtra("in");
        String identity="";
        try {
            JSONObject inJson=new JSONObject(in);
            identity=inJson.getString("identity");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String ident=identity;


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if("tea".equals(ident)){
                    Intent in=getIntent();
                    in.setClass(HomeActivity.this,TeacherActivity.class);
                    startActivity(in);
                }


            }
        });

        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("stu".equals(ident)){
                    Intent in=getIntent();
                    in.setClass(HomeActivity.this,StudentActivity.class);
                    startActivity(in);
                }
            }
        });
    }
}
