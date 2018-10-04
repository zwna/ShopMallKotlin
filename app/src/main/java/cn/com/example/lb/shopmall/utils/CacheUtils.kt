package cn.com.example.lb.shopmall.utils

import android.content.Context
import cn.com.example.lb.shopmall.app.ShopMallApplication

class CacheUtils {

    companion object {
        fun getString(key:String) = ShopMallApplication.context.getSharedPreferences("atguigu", Context.MODE_PRIVATE).getString(key,"")

        fun saveString(key:String,value:String) = ShopMallApplication.context.getSharedPreferences("atguigu", Context.MODE_PRIVATE).edit().putString(key,value).apply()
    }

}