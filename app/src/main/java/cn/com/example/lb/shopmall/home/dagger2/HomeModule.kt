package cn.com.example.lb.shopmall.home.dagger2

import cn.com.example.lb.shopmall.base.ActivityScope
import cn.com.example.lb.shopmall.home.api.HomeApi
import cn.com.example.lb.shopmall.home.bean.HomeDataBean
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import retrofit2.Retrofit

@Module
class HomeModule {

    @ActivityScope
    @Provides
    fun provideHomeApi(retrofit: Retrofit):HomeApi{
        return retrofit.create(HomeApi::class.java)
    }

    @ActivityScope
    @Provides
    fun provideObservable(homeApi: HomeApi):Observable<HomeDataBean>{
        return homeApi.getHomeInfo()
    }
}