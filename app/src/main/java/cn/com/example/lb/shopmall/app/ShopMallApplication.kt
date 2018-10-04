package cn.com.example.lb.shopmall.app

import android.app.Application
import android.content.Context
import cn.com.example.lb.shopmall.base.BaseApplicationComponent
import cn.com.example.lb.shopmall.base.BaseApplicationModule
import cn.com.example.lb.shopmall.base.DaggerBaseApplicationComponent
import io.reactivex.plugins.RxJavaPlugins

class ShopMallApplication : Application() {

    var baseApplicationComponent: BaseApplicationComponent? = null

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this@ShopMallApplication
        val baseApplicationModule = BaseApplicationModule(this)
        baseApplicationComponent = DaggerBaseApplicationComponent.builder().baseApplicationModule(baseApplicationModule).build()
        RxJavaPlugins.setErrorHandler { t -> System.out.println(t?.message) }
    }
}