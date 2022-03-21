package com.example.mvvmmvcmvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvc.MvcActivity;
import com.example.mvp.MvpActivity;
import com.example.mvvm.MvvmActivity;


public class MainActivity extends AppCompatActivity {

    private Button onMvc, onMvp, onMvvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onMvc = findViewById(R.id.mvc_button);
        onMvp = findViewById(R.id.mvp_button);
        onMvvm = findViewById(R.id.mvvm_button);

        onMvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMvp(view);
            }
        });

        onMvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMvc(view);
            }
        });

        onMvvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMvvm(view);
            }
        });
    }

    public void onMvc(View view){
        startActivity(MvcActivity.getIntent(this));
    }
    public void onMvp(View view){
        startActivity(MvpActivity.getIntent(this));
    }

    public void onMvvm(View view){
        startActivity(MvvmActivity.getIntent(this));
    }
}