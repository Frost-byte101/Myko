package com.example.watcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.fishaq.activity.FishBaseActivity;
import com.example.fishaq.annotation.ViewInject;
import com.example.fishaq.internet.FishHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends FishBaseActivity {

    @ViewInject(R.id.teacher)
    private RadioButton teacher;

    @ViewInject(R.id.student)
    private RadioButton student;

    @ViewInject(R.id.account)
    private EditText account;

    @ViewInject(R.id.password)
    private EditText password;

    @ViewInject(R.id.repassword)
    private EditText repassword;

    @Override
    public int initLayoutId() {
        return R.layout.activity_signup;
    }


    public void register(View view) {

        String usernameStr = account.getText().toString();
        String passwordStr = password.getText().toString();
        String repasswordStr = repassword.getText().toString();
        String identityStr = "";

        if(student.isChecked()){
            identityStr="stu";
        }
        if(teacher.isChecked()){
            identityStr="tea";
        }


        if(!passwordStr.equals(repasswordStr)){
            Toast.makeText(this, "Two password entries are inconsistent", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username",usernameStr);
            jsonObject.put("password",passwordStr);
            jsonObject.put("identity",identityStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        new FishHttp() {
            @Override
            public void OnCallBack(String result) {
                Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }.okPostBody(NetConfig.signup,jsonObject);


    }
}
