package cn.com.example.lb.shopmall.shoppingcart.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.app.ShopMallApplication
import cn.com.example.lb.shopmall.base.BaseFragment
import cn.com.example.lb.shopmall.home.bean.GoodsBean
import cn.com.example.lb.shopmall.shoppingcart.adapter.ShoppingCartAdapter
import cn.com.example.lb.shopmall.shoppingcart.dagger2.CartStorageModule
import cn.com.example.lb.shopmall.shoppingcart.dagger2.DaggerCartStorageComponent
import cn.com.example.lb.shopmall.shoppingcart.utils.CartStorage
import kotlinx.android.synthetic.main.empty_cart.*
import kotlinx.android.synthetic.main.fragment_shoppingcart.*
import javax.inject.Inject

class ShoppingCartFragment:BaseFragment() {

    private var shoppingCartView:View? = null

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var adapter: ShoppingCartAdapter

    private var  goodsBeanList:ArrayList<GoodsBean>? = null

    override fun initView(): View? {
        shoppingCartView = View.inflate(activity, R.layout.fragment_shoppingcart,null)
        return shoppingCartView
    }

    override fun initData() {

       showData()
    }

    private fun showData(){
        goodsBeanList = CartStorage.getAllData() as ArrayList<GoodsBean>
        val cartStorageModule = CartStorageModule(activity!!,goodsBeanList as ArrayList<GoodsBean>)
        DaggerCartStorageComponent.builder().cartStorageModule(cartStorageModule).baseApplicationComponent((activity?.application as ShopMallApplication).baseApplicationComponent).build().inject(this@ShoppingCartFragment)
        if(goodsBeanList != null && goodsBeanList!!.size > 0){
            ll_empty_shopcart.visibility = View.GONE
            recyclerview.adapter = adapter
            recyclerview.layoutManager = linearLayoutManager
        }else{
            ll_empty_shopcart.visibility = View.VISIBLE
        }
    }

}