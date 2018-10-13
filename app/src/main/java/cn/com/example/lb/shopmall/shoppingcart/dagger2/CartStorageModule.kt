package cn.com.example.lb.shopmall.shoppingcart.dagger2

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.widget.CheckBox
import android.widget.TextView
import cn.com.example.lb.shopmall.home.bean.GoodsBean
import cn.com.example.lb.shopmall.shoppingcart.adapter.ShoppingCartAdapter
import dagger.Module
import dagger.Provides

@Module
class CartStorageModule(val context: Context,val goodsBeanList:List<GoodsBean>?,val checkBoxAll:CheckBox,val tv_shopcart_total:TextView,val cb_all:CheckBox){

    @Provides
    fun provideShoppingCartAdapter() = ShoppingCartAdapter(context,goodsBeanList as? ArrayList<GoodsBean>,checkBoxAll,tv_shopcart_total,cb_all)

    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

}