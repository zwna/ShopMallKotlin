package cn.com.example.lb.shopmall.app

import android.app.Application
import cn.com.example.lb.shopmall.base.BaseApplicationComponent
import cn.com.example.lb.shopmall.base.BaseApplicationModule
import cn.com.example.lb.shopmall.base.DaggerBaseApplicationComponent

class ShopMallApplication:Application(){

    lateinit var baseApplicationComponent: BaseApplicationComponent

    override fun onCreate() {
        super.onCreate()
        val baseApplicationModule = BaseApplicationModule(this)
        baseApplicationComponent = DaggerBaseApplicationComponent.builder().baseApplicationModule(baseApplicationModule).build()
    }
}