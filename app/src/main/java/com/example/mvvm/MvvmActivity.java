package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

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

public class MvvmActivity extends AppCompatActivity {

    private final List<String> listValues = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private CountriesViewModel viewModel;
    private Button retryButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle("Mvvm Activity");

        //version problem with udemy course
        //viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);

        progressBar = findViewById(R.id.mvvmProgressBar);
        retryButton = findViewById(R.id.mvvmRetryButton);

        listView = findViewById(R.id.mvp_list);
        adapter = new ArrayAdapter<>(this,R.layout.row_layout, R.id.list_item, listValues);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MvvmActivity.this, "You clicked!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        observeViewModel();

    }

    private void observeViewModel(){
        viewModel.getCountries().observe((LifecycleOwner) this, countries ->{
            if (countries != null){
                listValues.clear();
                listValues.addAll(countries);
                listView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }else{
                listView.setVisibility(View.GONE);
            }
        });

        viewModel.getCountryError().observe((LifecycleOwner) this, error ->{
            progressBar.setVisibility(View.GONE);
            if (error){
                Toast.makeText(this, "Err", Toast.LENGTH_SHORT).show();
            }else{
                retryButton.setVisibility(View.GONE);
            }
        });
    }

    public void onRetry(View view){
        viewModel.onRefresh();
        listView.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public static Intent getIntent(Context context){
        return new Intent(context, MvvmActivity.class);
    }
}