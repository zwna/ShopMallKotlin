package cn.com.example.lb.shopmall.app

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.home.adapter.HomeFragmentAdapter
import cn.com.example.lb.shopmall.home.bean.GoodsBean
import cn.com.example.lb.shopmall.shoppingcart.utils.CartStorage
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_goods_info.*

class GoodsInfoActivity : Activity() {

    private lateinit var unbinder:Unbinder

    private var goodsBean: GoodsBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_info)
        unbinder = ButterKnife.bind(this@GoodsInfoActivity)
        goodsBean = intent.getSerializableExtra(HomeFragmentAdapter.TAG) as GoodsBean
        Toast.makeText(this,goodsBean.toString(),Toast.LENGTH_SHORT).show()
        setDataToView()
    }

    private fun setDataToView(){
        if(goodsBean != null) {
            Glide.with(this).load(NetUrlUtils.BASE_IMAGE_URL + goodsBean?.figure).into(iv_good_info_image)
            tv_good_info_name.text = goodsBean?.name
            tv_good_info_price.text = "￥${goodsBean?.cover_price}"
            setWebViewData(goodsBean?.product_id)
        }
    }

    private fun setWebViewData(product_id:String?)
    {
         if(product_id != null){
             wb_good_info_more.loadUrl("http://www.atguigu.com")
             val webViewSetting = wb_good_info_more.settings
             webViewSetting.javaScriptEnabled = true
             webViewSetting.useWideViewPort = true
             webViewSetting.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
             wb_good_info_more.webViewClient = WebViewClient()
         }
    }
    @OnClick(R.id.btn_more,R.id.tv_more_home,R.id.tv_more_search,R.id.tv_more_share,R.id.ib_good_info_back,R.id.ib_good_info_more,R.id.btn_good_info_addcart,R.id.tv_good_info_callcenter,R.id.tv_good_info_collection,R.id.tv_good_info_cart)
    fun onClickEvent(view:View){
        when(view.id){
            R.id.ib_good_info_back -> finish()
            R.id.ib_good_info_more -> Toast.makeText(this@GoodsInfoActivity,"更多",Toast.LENGTH_SHORT).show()
            R.id.btn_good_info_addcart ->  CartStorage.addData(goodsBean!!)
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
