package cn.com.example.lb.shopmall.home.dagger2

import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import dagger.Component

@Component(modules = [(ChannelModule::class)])
interface ChannelComponent {

    fun inject(channelViewHolder: HomeFragmentAdapter.ChannelViewHolder)
}