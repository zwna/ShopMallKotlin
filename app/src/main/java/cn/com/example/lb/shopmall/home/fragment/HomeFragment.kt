package cn.com.example.lb.shopmall.home.fragment

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.app.ShopMallApplication
import cn.com.example.lb.shopmall.base.BaseFragment
import cn.com.example.lb.shopmall.base.DaggerApplicationComponent
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.titlebar.*
import retrofit2.Retrofit
import javax.inject.Inject

class HomeFragment:BaseFragment() {

    private var homeView:View? = null

    private lateinit var unbinder:Unbinder

    @Inject
    lateinit var retrofit:Retrofit

    override fun initView(): View? {
        homeView = View.inflate(activity, R.layout.fragment_home,null)
        unbinder = ButterKnife.bind(this, homeView!!)
        return homeView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        DaggerApplicationComponent.create().inject(this)
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

    }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

}