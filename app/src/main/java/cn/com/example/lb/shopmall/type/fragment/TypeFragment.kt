package cn.com.example.lb.shopmall.type.fragment

import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.base.BaseFragment

class TypeFragment:BaseFragment() {
    private var homeView:View? = null

    private lateinit var unbinder: Unbinder

    override fun initView(): View? {
        homeView = View.inflate(activity, R.layout.fragment_home,null)
        unbinder = ButterKnife.bind(this, homeView!!)
        return homeView
    }

    override fun initData() {

    }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }
}