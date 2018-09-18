package cn.com.example.lb.shopmall.home.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.app.ShopMallApplication
import cn.com.example.lb.shopmall.base.BaseFragment
import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import cn.com.example.lb.shopmall.home.api.HomeApi
import cn.com.example.lb.shopmall.home.bean.HomeDataBean
import cn.com.example.lb.shopmall.home.dagger2.DaggerHomeComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.titlebar.*
import retrofit2.Retrofit
import javax.inject.Inject

class HomeFragment:BaseFragment() {

    private var homeView:View? = null

    private lateinit var unbinder:Unbinder

    @Inject
    lateinit var retrofit:Retrofit

    @Inject
    lateinit var homeApi:HomeApi

    @Inject
    lateinit var observable: Observable<HomeDataBean>

    private lateinit var homeFragmentAdapter:HomeFragmentAdapter


    override fun initView(): View? {
        homeView = View.inflate(activity, R.layout.fragment_home,null)
        unbinder = ButterKnife.bind(this, homeView!!)
        return homeView
    }


    private fun initListener(){
        ib_top.setOnClickListener{
            view -> rv_home.scrollToPosition(0)
        }

        tv_search_home.setOnClickListener{
            view ->
        }

        tv_message_home.setOnClickListener{
            view ->

        }
    }

    override fun initData() {
        initListener()
        DaggerHomeComponent.builder().baseApplicationComponent((activity?.application as ShopMallApplication).baseApplicationComponent).build().inject(this)
        observable.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    t:HomeDataBean? ->
                    addDataToView(t)
                    println(t)
                },{t: Throwable? ->  println("throwable == ${t.toString()}")})
    }

    private fun addDataToView(homeDataBean: HomeDataBean?){
        if(homeDataBean != null){
            //有数据
            homeFragmentAdapter = HomeFragmentAdapter(activity as Context,homeDataBean.result)
            rv_home.layoutManager = GridLayoutManager(activity,1)
            rv_home.adapter = homeFragmentAdapter
        }else{
            //无数据
        }
    }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

}