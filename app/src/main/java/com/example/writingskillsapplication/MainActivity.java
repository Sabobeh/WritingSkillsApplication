package com.example.writingskillsapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASS";
    public static final String FLAG = "FLAG";
    public static final String MyPREFERENCES = "MyPrefs" ;
    private boolean flag = false;
    private TextInputLayout edtName;
    private TextInputLayout edtPassword;
    private CheckBox chk;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupViews();
        setupSharedPrefs();
        checkPrefs();
    }

    private void checkPrefs() {
        flag = sharedpreferences.getBoolean(FLAG, false);

        if(flag){
            String name = sharedpreferences.getString(NAME, "");
            String password = sharedpreferences.getString(PASSWORD, "");
            edtName.getEditText().setText(name);
//            Toast.makeText(MainActivity.this,"Y",Toast.LENGTH_LONG).show();
            edtPassword.getEditText().setText(password);
            chk.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        sharedpreferences= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    private void setupViews() {
//        edtName = findViewById(R.id.username);
        edtName = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        chk = findViewById(R.id.chck);
    }
    public void btnLoginOnClick(View view) {


        String name = edtName.getEditText().getText().toString();
        String password = edtPassword.getEditText().getText().toString();
        if(chk.isChecked()){
            if(!flag) {
                editor.putString(NAME, name);
                editor.putString(PASSWORD, password);
                editor.putBoolean(FLAG, true);
                editor.commit();
                Toast.makeText(MainActivity.this,"Your Login info is saved",Toast.LENGTH_LONG).show();
            }
        }
        else {
            editor.clear();
            editor.commit();
            Toast.makeText(MainActivity.this,"Saved Login info is cleared",Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, HomeScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}