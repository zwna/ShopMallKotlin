package cn.com.example.lb.shopmall.base

import cn.com.example.lb.shopmall.app.ShopMallApplication
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [(BaseApplicationModule::class)])
interface BaseApplicationComponent {

   fun retrofit():Retrofit
}