package cn.com.example.lb.shopmall.home.dagger2

import android.content.Context
import cn.com.example.lb.shopmall.home.adapter.ChannelAdapter
import cn.com.example.lb.shopmall.home.bean.ChannelInfo
import dagger.Module
import dagger.Provides

@Module
class ChannelModule(var context: Context,var channelInfoList:List<ChannelInfo>) {

    @Provides
    fun provideChannelAdapter() = ChannelAdapter(context,channelInfoList)



}