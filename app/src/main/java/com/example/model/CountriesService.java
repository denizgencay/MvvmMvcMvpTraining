package com.example.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {

    public static final String BASE_URL = "https://restcountries.com/v2/";

    private CountriesApi countriesApi;

    public CountriesService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        countriesApi = retrofit.create(CountriesApi.class);
    }

    public Single<List<Country>> getCountries(){
        return countriesApi.getCountries();
    }
}
