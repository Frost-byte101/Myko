package com.example.watcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fishaq.activity.FishBaseAppCompatActivity;
import com.example.fishaq.annotation.ViewInject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessActivity extends FishBaseAppCompatActivity {

    @ViewInject(R.id.progress)
    private ProgressBar progress;

    @ViewInject(R.id.time)
    private TextView time;

    @ViewInject(R.id.status)
    private TextView status;

    @ViewInject(R.id.rate)
    private TextView rate;


    @Override
    public int initLayoutId() {
        return R.layout.activity_page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress.setProgress(50);
        setTitle("ProgressRate");

        Intent in=getIntent();
        String startTime = in.getStringExtra("startTime");
        String endTime = in.getStringExtra("endTime");

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateStart = simpleDateFormat.parse(startTime);
            Date dateEnd = simpleDateFormat.parse(endTime);
            long end=dateEnd.getTime();
            long start=dateStart.getTime();
            long time = end-start;
            Date date=new Date();
            long now=date.getTime();
            if(now>=dateEnd.getTime()){
                status.setText("Finished");
                progress.setProgress(100);
                rate.setText("100%");

            }else if(now<=dateStart.getTime()){
                status.setText("HasNotStarted");
                progress.setProgress(0);
                rate.setText("0%");
            }else {
                int i = (int) ((float) (now - dateStart.getTime()) / time * 100);

                progress.setProgress(i);
                rate.setText(i+"%");
            }



        } catch (ParseException e) {
            e.printStackTrace();
        }

        time.setText(startTime+" To "+endTime);


    }
}
