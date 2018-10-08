package cn.com.example.lb.shopmall.shoppingcart.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.customview.AddSubView
import cn.com.example.lb.shopmall.home.bean.GoodsBean
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide

class ShoppingCartAdapter(val context:Context,val goodsBeanList:List<GoodsBean>):RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    lateinit var itemView:View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemView = View.inflate(context, R.layout.item_shop_cart,null)
        val viewHolder = ViewHolder(itemView)
        return viewHolder
    }

    override fun getItemCount() = goodsBeanList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goodsBean = goodsBeanList[position]
        goodsBean.isSelected = true
        holder.cb_gov.isChecked = goodsBean.isSelected
        Glide.with(itemView).load(NetUrlUtils.BASE_IMAGE_URL+goodsBean.figure).into(holder.iv_gov)
        holder.tv_desc_gov.text = goodsBean.name
        holder.tv_price_gov.text = "￥${goodsBean.cover_price}"
    }


    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        @BindView(R.id.cb_gov)
        lateinit var cb_gov:CheckBox
        @BindView(R.id.iv_gov)
        lateinit var iv_gov:ImageView
        @BindView(R.id.tv_desc_gov)
        lateinit var tv_desc_gov:TextView
        @BindView(R.id.tv_price_gov)
        lateinit var tv_price_gov:TextView
        @BindView(R.id.numberAddSubView)
        lateinit var numberAddSubView:AddSubView

        init {
            ButterKnife.bind(this@ViewHolder,itemView)
        }
    }
}