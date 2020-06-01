package com.example.optuspoc.networkLayer

import com.example.optuspoc.utility.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
* Object is to create instance of Retrofit
* */
object ApiClient {

    private var servicesApiInterface: ApiInterface? = null


    /**
     * This method is initialize for retrofit object
     * @return APIInterface instance
     */
    fun build(): ApiInterface? {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())
        httpClient.readTimeout(60,TimeUnit.SECONDS)
        httpClient.connectTimeout(60,TimeUnit.SECONDS)

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ApiInterface::class.java
        )

        return servicesApiInterface as ApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}