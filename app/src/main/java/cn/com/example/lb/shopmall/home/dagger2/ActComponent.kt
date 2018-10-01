package cn.com.example.lb.shopmall.home.dagger2

import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import dagger.Component

@Component(modules = [(ActModule::class)])
interface ActComponent {

    fun inject(actViewHolder: HomeFragmentAdapter.ActViewHolder)
}