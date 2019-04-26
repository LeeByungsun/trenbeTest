package com.trenbe.test

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.trenbe.test.GlobalStatic.DEBUG
import com.trenbe.test.network.TrenbeAPIInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class TestApplication : Application() {
    private var context: Context? = null


    override fun onCreate() {
        super.onCreate()
        instance = this
        this.context = applicationContext
        setRetrofitServer(DEBUG)
    }

    /**
     * retrofit setting
     */
    private fun setRetrofitServer(debug: Boolean) {
        retrofitServer = if (debug) {
            val httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            Retrofit.Builder().baseUrl(TrenbeAPIInfo.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
        } else {
            Retrofit.Builder().baseUrl(TrenbeAPIInfo.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        trenbeApiServer = retrofitServer!!.create(TrenbeAPIInfo::class.java)
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: TestApplication? = null
            private set
        var retrofitServer: Retrofit? = null
        var density: Float? = null
        lateinit var trenbeApiServer: TrenbeAPIInfo
    }

}
