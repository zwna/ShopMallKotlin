package cn.com.example.lb.shopmall.app

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import cn.com.example.lb.shopmall.home.bean.GoodsBean

class GoodsInfoActivity : Activity() {

    private lateinit var unbinder:Unbinder

    private lateinit var goodsBean: GoodsBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_info)
        unbinder = ButterKnife.bind(this@GoodsInfoActivity)
        goodsBean = intent.getSerializableExtra(HomeFragmentAdapter.TAG) as GoodsBean

        Toast.makeText(this,goodsBean.toString(),Toast.LENGTH_SHORT).show()
    }

    @OnClick(R.id.btn_more,R.id.tv_more_home,R.id.tv_more_search,R.id.tv_more_share,R.id.ib_good_info_back,R.id.ib_good_info_more,R.id.btn_good_info_addcart,R.id.tv_good_info_callcenter,R.id.tv_good_info_collection,R.id.tv_good_info_cart)
    fun onClickEvent(view:View){
        when(view.id){
            R.id.ib_good_info_back -> finish()
            R.id.ib_good_info_more -> Toast.makeText(this@GoodsInfoActivity,"更多",Toast.LENGTH_SHORT).show()
            R.id.btn_good_info_addcart -> Toast.makeText(this@GoodsInfoActivity,"添加到购物车",Toast.LENGTH_SHORT).show()
            R.id.tv_good_info_callcenter -> Toast.makeText(this@GoodsInfoActivity,"客户中心",Toast.LENGTH_SHORT).show()
            R.id.tv_good_info_collection -> Toast.makeText(this@GoodsInfoActivity,"收藏",Toast.LENGTH_SHORT).show()
            R.id.tv_good_info_cart -> Toast.makeText(this@GoodsInfoActivity,"购物车",Toast.LENGTH_SHORT).show()
            R.id.btn_more -> Toast.makeText(this@GoodsInfoActivity,"购物车",Toast.LENGTH_SHORT).show()
            R.id.tv_more_home -> Toast.makeText(this@GoodsInfoActivity,"主页面",Toast.LENGTH_SHORT).show()
            R.id.tv_more_search -> Toast.makeText(this@GoodsInfoActivity,"搜索",Toast.LENGTH_SHORT).show()
            R.id.tv_more_share -> Toast.makeText(this@GoodsInfoActivity,"分享",Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        unbinder.unbind()
        super.onDestroy()
    }
}
