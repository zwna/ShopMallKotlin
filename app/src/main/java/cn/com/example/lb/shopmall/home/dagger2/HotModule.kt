package cn.com.example.lb.shopmall.home.dagger2

import android.content.Context
import cn.com.example.lb.shopmall.home.adapter.HotGridViewAdapter
import cn.com.example.lb.shopmall.home.bean.HotInfo
import dagger.Module
import dagger.Provides

@Module
class HotModule(val context: Context,val hotInfoList:List<HotInfo>) {

    @Provides
    fun provideHotGridViewAdapter() = HotGridViewAdapter(context,hotInfoList)
}