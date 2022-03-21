package com.example.mvp;

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

public class MvpActivity extends AppCompatActivity implements CountriesPresenter.View {

    private final List<String> listValues = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private CountriesPresenter presenter;
    private Button retryButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setTitle("MvpActivity");

        presenter = new CountriesPresenter(this);

        progressBar = findViewById(R.id.progressBarMvp);
        retryButton = findViewById(R.id.retryButtonMvp);

        listView = findViewById(R.id.mvp_list);
        adapter = new ArrayAdapter<>(this,R.layout.row_layout, R.id.list_item, listValues);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MvpActivity.this, "You clicked!!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static Intent getIntent(Context context){
        return new Intent(context, MvpActivity.class);
    }

    @Override
    public void setListValues(List<String> countries) {
        listValues.clear();
        listValues.addAll(countries);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
    }
    public void onRetry(View view){
        presenter.onRefresh();
        listView.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }


}