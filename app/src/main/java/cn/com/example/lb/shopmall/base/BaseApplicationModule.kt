package cn.com.example.lb.shopmall.base

import android.content.Context
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class BaseApplicationModule(var context: Context){

    @Singleton
    @Provides
    fun  provideOkHttpClient():OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                .readTimeout(10000,TimeUnit.MILLISECONDS)
                .build()!!

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(NetUrlUtils.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()!!

    @Provides
    fun provideContext() = context

}