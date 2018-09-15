package cn.com.example.lb.shopmall.home.dagger2

import cn.com.example.lb.shopmall.base.ActivityScope
import cn.com.example.lb.shopmall.base.BaseApplicationComponent
import cn.com.example.lb.shopmall.home.fragment.HomeFragment
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Component(modules = [(HomeModule::class)],dependencies = [(BaseApplicationComponent::class)])
interface HomeComponent {

     fun inject(homeFragment: HomeFragment)
}