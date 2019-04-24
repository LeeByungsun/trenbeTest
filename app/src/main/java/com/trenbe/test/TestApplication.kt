package com.trenbe.test

import android.app.Application
import android.content.Context
import com.buggyani.riiid.network.TrenbeAPIInfo
import com.trenbe.test.GlobalStatic.DEBUG

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bslee on 2019-04-124
 */
class TestApplication : Application() {
    private val TAG = javaClass.simpleName
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
    fun setRetrofitServer(debug: Boolean) {
        retrofitServer = if (debug) {
            val httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY
//        logging.level = HttpLoggingInterceptor.Level.HEADERS
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
        var instance: TestApplication? = null
            private set
        var retrofitServer: Retrofit? = null
        lateinit var trenbeApiServer: TrenbeAPIInfo
    }

}
