package cn.com.example.lb.shopmall.shoppingcart.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
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

class ShoppingCartFragment:BaseFragment(),View.OnClickListener {

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_delete -> {
                adapter.deleteData()
                adapter.checkAll()
                if(adapter.itemCount == 0){
                    emptyShoppingCart()
                }
            }
        }
    }

    private var shoppingCartView:View? = null

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var adapter: ShoppingCartAdapter

    private var  goodsBeanList:ArrayList<GoodsBean>? = null

    private companion object {
         const val ACTION_EDIT = 1
         const val ACTION_COMPLETE = 2
     }

    override fun initView(): View? {
        shoppingCartView = View.inflate(activity, R.layout.fragment_shoppingcart,null)
        return shoppingCartView
    }

    private fun initListener() {
        btn_delete.setOnClickListener(this@ShoppingCartFragment)
         tv_shopcart_edit.tag = ACTION_EDIT
        tv_shopcart_edit.setOnClickListener {
            v: View? ->
            val action = tv_shopcart_edit.tag as? Int
            if(action == ACTION_EDIT){
               showDelete()
            }else{
               hideDelete()
            }
        }
    }

    private fun hideDelete() {
        tv_shopcart_edit.tag = ACTION_EDIT
        tv_shopcart_edit.text = "编辑"
        adapter.checkAll_None(true)
        adapter.checkAll()
        adapter.showTotalPrice()
        ll_delete.visibility = View.GONE
        ll_check_all.visibility = View.VISIBLE
    }

    private fun showDelete() {
        tv_shopcart_edit.tag = ACTION_COMPLETE
        tv_shopcart_edit.text = "完成"
        adapter.checkAll_None(false)
        adapter.checkAll()
        ll_delete.visibility = View.VISIBLE
        ll_check_all.visibility = View.GONE
    }

    override fun initData() {
        showData()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun showData(){
        goodsBeanList = CartStorage.getAllData() as ArrayList<GoodsBean>
        val cartStorageModule = CartStorageModule(activity!!,goodsBeanList as ArrayList<GoodsBean>,checkbox_all,tv_shopcart_total,cb_all)
        DaggerCartStorageComponent.builder().cartStorageModule(cartStorageModule).baseApplicationComponent((activity?.application as ShopMallApplication).baseApplicationComponent).build().inject(this@ShoppingCartFragment)
        if(goodsBeanList?.isNotEmpty()!!){
            tv_shopcart_edit.visibility = View.VISIBLE
            ll_check_all.visibility = View.VISIBLE
            ll_empty_shopcart.visibility = View.GONE
            recyclerview.adapter = adapter
            recyclerview.layoutManager = linearLayoutManager
        }else{
            emptyShoppingCart()
        }
    }

    private fun emptyShoppingCart(){
        ll_empty_shopcart.visibility = View.VISIBLE
        tv_shopcart_edit.visibility = View.GONE
        ll_delete.visibility = View.GONE
    }

}