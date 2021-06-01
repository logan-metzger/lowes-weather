package com.logan.lowesweather_loganmetzger.remote

import com.logan.lowesweather_loganmetzger.BuildConfig
import com.logan.lowesweather_loganmetzger.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder =
            OkHttpClient.Builder().connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS).readTimeout(
                Constants.TIMEOUT,
                TimeUnit.SECONDS
            ).writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return clientBuilder.build()
    }

    fun providesRetrofitService(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(providesOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

}