package com.example.ridesupportmobil;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class prueba {

    private static final String BASE_URL = "http://35.224.135.194:5000/graphql";

    public static ApolloClient setupApollo(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
    }

}