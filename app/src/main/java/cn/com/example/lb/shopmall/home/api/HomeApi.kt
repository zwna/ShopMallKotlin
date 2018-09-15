package cn.com.example.lb.shopmall.home.api

import cn.com.example.lb.shopmall.home.bean.HomeDataBean
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeApi {

    @GET("atguigu/json/HOME_URL.json")
    fun getHomeInfo():Observable<HomeDataBean>
}