package com.example.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.mvvmmvcmvp.R;

import java.util.ArrayList;
import java.util.List;

public class MvcActivity extends AppCompatActivity {

    private List<String> listValues = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private CountriesController controller;
    private Button retryButton;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        setTitle("MvcActivity");

        controller = new CountriesController(this);

        progressBar = findViewById(R.id.progressBar);
        retryButton = findViewById(R.id.retryButton);

        listView = findViewById(R.id.mvc_list);
        adapter = new ArrayAdapter<>(this,R.layout.row_layout, R.id.list_item, listValues);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MvcActivity.this, "You clicked!!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setListValues(List<String> values){
        listValues.clear();
        listValues.addAll(values);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    public void onRetry(View view){
        controller.onRefresh();
        listView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onError(){
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
    }

    public static Intent getIntent(Context context){
        return new Intent(context, MvcActivity.class);
    }
}