package cn.com.example.lb.shopmall.home.dagger2

import android.content.Context
import cn.com.example.lb.shopmall.home.adapter.RecommendGridViewAdapter
import cn.com.example.lb.shopmall.home.bean.RecommendInfo
import dagger.Module
import dagger.Provides

@Module
class RecommendModule(val context:Context,val recommendInfoList:List<RecommendInfo>) {

    @Provides
    fun provideRecommendGridViewAdapter() = RecommendGridViewAdapter(context,recommendInfoList)
}