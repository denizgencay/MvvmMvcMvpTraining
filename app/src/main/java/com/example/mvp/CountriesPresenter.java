package com.example.mvp;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.model.CountriesService;
import com.example.model.Country;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesPresenter {

    private final View view;
    private final CountriesService service;

    public CountriesPresenter(View view){
        this.view = view;
        service = new CountriesService();
        fetchCountries();
    }

    @SuppressLint("CheckResult")
    private void fetchCountries(){
        service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {
                    @Override
                    public void onSuccess(List<Country> countries) {
                        List<String> countryNames= new ArrayList<>();
                        for (Country country: countries){
                            countryNames.add(country.countryName);
                        }
                        view.setListValues(countryNames);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.toString());
                        System.out.println(e);
                    }
                });
    }

    public void onRefresh(){
        fetchCountries();
    }

    public interface View{
        void setListValues(List<String> countries);
        void onError(String errorMessage);
    }

}
