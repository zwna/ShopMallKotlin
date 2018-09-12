package cn.com.example.lb.shopmall.base

import cn.com.example.lb.shopmall.app.ShopMallApplication
import cn.com.example.lb.shopmall.home.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(homeFragment: HomeFragment)
}