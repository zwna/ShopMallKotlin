package cn.com.example.lb.shopmall.home.dagger2

import cn.com.example.lb.shopmall.base.ActivityScope
import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import dagger.Component

@ActivityScope
@Component(modules = [RecommendModule::class])
interface RecommendComponent {

    fun inject(recommendViewHolder: HomeFragmentAdapter.RecommendViewHolder)
}