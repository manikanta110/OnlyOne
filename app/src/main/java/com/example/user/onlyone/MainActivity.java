package com.example.user.onlyone;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button dialog,customtoast,inflater,filter;
    EditText email,password;
    Button login,cancel;
    String logintext = "manikanta@gmail.com";
    String pwdtext = "manikanta110";
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
    }

    private void initviews() {
        dialog = (Button)findViewById(R.id.btn_dialog);
        customtoast = (Button)findViewById(R.id.btn_toast);
        inflater = (Button)findViewById(R.id.btn_inflater);
       filter = (Button)findViewById(R.id.btn_checkfilter);
        inflater.setOnClickListener(this);
        filter.setOnClickListener(this);
        dialog.setOnClickListener(this);
        customtoast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_dialog:
                openAlertdialog();
                break;

            case R.id.btn_toast:
                openCustomToast();
                break;
            case R.id.btn_inflater:
                Toast.makeText(this, "we removed Inflater Feature", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_checkfilter:
                openfilter();
                break;
                default:
                    break;
        }
    }

    private void openfilter() {

        Intent intent = new Intent(MainActivity.this,ActivityFilter.class);
        startActivity(intent);
    }



    private void openCustomToast() {

        Toast toast =  new Toast(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast,null);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }

    private void openAlertdialog() {
        AlertDialog.Builder mbuilder =  new AlertDialog.Builder(this);
        LayoutInflater inflater =  getLayoutInflater();
        View view=inflater.inflate(R.layout.custom_alertdialog,null);
        mbuilder.setView(view);
        final AlertDialog dialog = mbuilder.create();
        dialog.show();
        email = (EditText)view.findViewById(R.id.email_edit);
        password = (EditText)view.findViewById(R.id.pwd_edit);
        login = (Button)view.findViewById(R.id.btn_login);
        cancel = (Button)view.findViewById(R.id.btn_cancel);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(email.getText().toString().equals(logintext)&& password.getText().toString().equals(pwdtext)){
                  Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

              }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


    }
}
