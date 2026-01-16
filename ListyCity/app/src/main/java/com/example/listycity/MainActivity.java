package com.example.listycity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;
    EditText cityInput;
    Button addButton, deleteButton, confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addButton = findViewById(R.id.btn_add_city);
        deleteButton = findViewById(R.id.btn_delete_city);
        confirmButton = findViewById(R.id.btn_confirm);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi", "Montreal"};

        datalist = new ArrayList<>();
        datalist.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, datalist);
        cityList.setAdapter(cityAdapter);

        addButton.setOnClickListener(view -> {
            cityInput.setText("");
            cityInput.requestFocus();
        });

        confirmButton.setOnClickListener(view -> {
            String newCity = cityInput.getText().toString().trim();
            if (TextUtils.isEmpty(newCity)) {
                return;
            }
            datalist.add(newCity);
            cityAdapter.notifyDataSetChanged();
            cityInput.setText("");
        });

        deleteButton.setOnClickListener(view -> {
            if (!datalist.isEmpty()) {
                datalist.remove(datalist.size() - 1);
                cityAdapter.notifyDataSetChanged();
            }
        });
    }
}