package cn.com.example.lb.shopmall.home.dagger2

import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import dagger.Component


@Component(modules = [(HotModule::class)])
interface HotComponent {

    fun inject(hotViewHolder: HomeFragmentAdapter.HotViewHolder)
}