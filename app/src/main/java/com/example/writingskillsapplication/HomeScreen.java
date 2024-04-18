package com.example.writingskillsapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeScreen extends AppCompatActivity {
    private TextView txtShowMessage;
    private Spinner spnMenu;
    private Button btnSearch;
    private String name;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();

        txtShowMessage = findViewById(R.id.txtMessagee);
        String msg = intent.getStringExtra("name");
        name = msg;
        txtShowMessage.setText("Welcome "+name+"! This app will help you develop " +
                "your Writing Skills in English language to be prepared for writing full essays in you exams.");
        setupViews();
        populateSpinner();
    }
    private void populateSpinner() {
        DatabaseItems db = new DatabaseItems();
        String[] cats = db.getLevels();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cats);
        spnMenu.setAdapter(adapter);
    }

    private void setupViews() {
        spnMenu = findViewById(R.id.spnMenu);
        btnSearch = findViewById(R.id.btnSearch);
    }

    public void btnContOnClick(View view) {
        String level = spnMenu.getSelectedItem().toString();
//        Toast.makeText(HomeScreen.this,skill,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Activity4.class);
        intent.putExtra("level", level);
        intent.putExtra("name", name);
//        Toast.makeText(HomeScreen.this,skill+"-"+name,Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}