package com.example.watcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.fishaq.activity.login.FishLoginActivity;
import com.example.fishaq.internet.FishHttp;

import org.json.JSONException;
import org.json.JSONObject;



public class LoginActivity extends FishLoginActivity {


    @Override
    protected void loginToDo(String account, String password) {
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("username",account);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new FishHttp() {
            @Override
            public void OnCallBack(String result) {
                try {
                    JSONObject jsonObject1 = new JSONObject(result);
                    Intent intent=new Intent();
                    if("stu".equals(jsonObject1.getString("identity"))){
                        intent.setClass(LoginActivity.this,HomeActivity.class);
                    }
                    if("tea".equals(jsonObject1.getString("identity"))){
                        intent.setClass(LoginActivity.this,HomeActivity.class);
                    }
                    intent.putExtra("in", result);
                    startActivity(intent);
                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }.okPostBody(NetConfig.signin,jsonObject);
    }

    @Override
    protected void registerToDo() {

        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);

    }

    @Override
    protected void retrieveToDo() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_signin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign In");

    }
}
