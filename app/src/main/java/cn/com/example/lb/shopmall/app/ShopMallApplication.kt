package cn.com.example.lb.shopmall.app

import android.app.Application
import cn.com.example.lb.shopmall.base.BaseApplicationComponent
import cn.com.example.lb.shopmall.base.BaseApplicationModule
import cn.com.example.lb.shopmall.base.DaggerBaseApplicationComponent
import io.reactivex.plugins.RxJavaPlugins

class ShopMallApplication:Application(){

    lateinit var baseApplicationComponent: BaseApplicationComponent

    override fun onCreate() {
        super.onCreate()
        val baseApplicationModule = BaseApplicationModule(this)
        baseApplicationComponent = DaggerBaseApplicationComponent.builder().baseApplicationModule(baseApplicationModule).build()
        RxJavaPlugins.setErrorHandler { t -> System.out.println(t?.message) }

    }
}