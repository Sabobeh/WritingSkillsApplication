package com.example.writingskillsapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Activity4 extends AppCompatActivity {
    private TextView txtShowMessage;
    private String name;
    private String level;
    private ListView lstItems;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.third2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        txtShowMessage = findViewById(R.id.txtMessage2);
        String Messagedname = intent.getStringExtra("name");
        String Messagedlevel = intent.getStringExtra("level");
        name = Messagedname;
        level = Messagedlevel;
        txtShowMessage.setText("Alright "+name+", Since you are "+ level+
                ", you need to work a little more on your skills and we suggest the following resources.");

        switch (level) {
            case "Advanced":
                txtShowMessage.setText("Alright " + name + ", Since you are " + level +
                        ", you need to work a little more on your skills and we suggest the following resources.");
                break;
            case "Intermediate":
                txtShowMessage.setText("Alright " + name + ", Since you are " + level +
                        ", you need to work a more on your skills and we suggest the following resources.");
                break;
            case "Beginner":
                txtShowMessage.setText("Alright " + name + ", Since you are " + level +
                        ", you need to work a lot more on your skills and we suggest the following resources.");
                break;
        }
        setupViews();

        DatabaseItems db = new DatabaseItems();
        String cat = level;

        List<Resource> result = db.getMenuItems(cat);
        Resource[] arr = result.toArray(new Resource[result.size()]);
        ArrayAdapter<Resource> adapter = new ArrayAdapter<Resource>(Activity4.this,
                android.R.layout.simple_list_item_1, arr);
        lstItems.setAdapter(adapter);

        lstItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Activity4.this, "I: "+adapter.getItem(position), Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse(adapter.getItem(position)+""); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    private void setupViews() {
        lstItems = findViewById(R.id.lstItems);
    }
}