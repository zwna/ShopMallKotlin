package cn.com.example.lb.shopmall.home.dagger2

import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import dagger.Component

@Component(modules = [(CountDownModule::class)])
interface CountDownComponent {

    fun inject(countDownViewHolder: HomeFragmentAdapter.CountDownViewHolder)
}