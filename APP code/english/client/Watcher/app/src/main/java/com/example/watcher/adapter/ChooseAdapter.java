package com.example.watcher.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fishaq.internet.FishHttp;
import com.example.watcher.NetConfig;
import com.example.watcher.R;
import com.example.watcher.beans.Choose;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChooseAdapter extends BaseAdapter {

    private List<Choose> list;
    private Context context;
    private String teaid;

    public ChooseAdapter(List<Choose> list, Context context,String teaid) {
        this.list = list;
        this.context = context;
        this.teaid = teaid;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final int pos=i;

        View view1=View.inflate(context, R.layout.item_choose,null);
        TextView teaUsername = view1.findViewById(R.id.tea_username);
        teaUsername.setText(list.get(i).getUsername());

        TextView teaStatus = view1.findViewById(R.id.tea_status);
        teaStatus.setText(list.get(i).getStatus());

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("stuid",list.get(pos).getId());
                    jsonObject.put("teaid",teaid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if("Not selected".equals(list.get(pos).getStatus())){
                    new FishHttp() {
                        @Override
                        public void OnCallBack(String result) {
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            list.get(pos).setStatus("Chosen");
                            ChooseAdapter.this.notifyDataSetChanged();
                        }
                    }.okPostBody(NetConfig.link,jsonObject);
                }else {
                    new FishHttp() {
                        @Override
                        public void OnCallBack(String result) {
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                            if("Successfully untie the relationship".equals(result)){
                                list.get(pos).setStatus("Not selected");
                            }
                            ChooseAdapter.this.notifyDataSetChanged();
                        }
                    }.okPostBody(NetConfig.closelink,jsonObject);
                }
            }
        });

        return view1;
    }
}
