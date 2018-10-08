package cn.com.example.lb.shopmall.shoppingcart.dagger2

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import cn.com.example.lb.shopmall.home.bean.GoodsBean
import cn.com.example.lb.shopmall.shoppingcart.adapter.ShoppingCartAdapter
import dagger.Module
import dagger.Provides

@Module
class CartStorageModule(val context: Context,val goodsBeanList:List<GoodsBean>?){

    @Provides
    fun provideShoppingCartAdapter() = ShoppingCartAdapter(context,goodsBeanList!!)

    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

}