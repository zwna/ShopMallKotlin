package cn.com.example.lb.shopmall.home.dagger2

import android.content.Context
import cn.com.example.lb.shopmall.home.adapter.CountDownRecyclerViewAdapter
import cn.com.example.lb.shopmall.home.bean.X
import dagger.Module
import dagger.Provides

@Module
class CountDownModule(val context: Context,val xList: List<X>) {

    @Provides
    fun provideCountDownRecyclerAdapter() = CountDownRecyclerViewAdapter(context,xList)
}