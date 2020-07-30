package com.example.watcher.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.watcher.R;
import com.example.watcher.SetTaskActivity;
import com.example.watcher.beans.Tea;

import java.util.List;

public class TeacherAdapter extends BaseAdapter {

    private Context context;
    private List<Tea> teaList;
    private String fId;

    public TeacherAdapter(Context context, List<Tea> teaList, String fId) {
        this.context = context;
        this.teaList = teaList;
        this.fId=fId;
    }

    @Override
    public int getCount() {
        return teaList.size();
    }

    @Override
    public Object getItem(int i) {
        return teaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=View.inflate(context, R.layout.item_teacher,null);
        TextView teaUsername = view1.findViewById(R.id.stu_username);
        TextView teaStatus = view1.findViewById(R.id.stu_status);

        LinearLayout stuItem=view1.findViewById(R.id.stu_item);

        teaUsername.setText(teaList.get(i).getUsername());
        teaStatus.setText(teaList.get(i).getStatus());

        final int pos=i;

        stuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SetTaskActivity.class);
                intent.putExtra("teaid",fId);
                intent.putExtra("stuid",teaList.get(pos).getId());
                context.startActivity(intent);

            }
        });


        return view1;
    }
}
