package cn.com.example.lb.shopmall.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.home.bean.X
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide

class CountDownRecyclerViewAdapter(val context:Context,val xList: List<X>):RecyclerView.Adapter<CountDownRecyclerViewAdapter.ViewHolder>() {

     var onCountDownRecycleView: OnCountDownRecycleView? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(View.inflate(context, R.layout.item_seckill,null))

    override fun getItemCount() = xList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (cover_price, figure, name, origin_price, product_id) = xList[position]
        Glide.with(context).load(NetUrlUtils.BASE_IMAGE_URL + figure).into(holder.iv_figure)
        holder.tv_cover_price.text = cover_price
        holder.tv_origin_price.text = origin_price
    }


    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        @BindView(R.id.iv_figure)
        lateinit var iv_figure:ImageView

        @BindView(R.id.tv_cover_price)
        lateinit var tv_cover_price:TextView

        @BindView(R.id.tv_origin_price)
        lateinit var tv_origin_price:TextView

        init {
            ButterKnife.bind(this,itemView)
            itemView.setOnClickListener {
                view ->
                if(onCountDownRecycleView != null){
                    onCountDownRecycleView?.onItemClick(layoutPosition)
                }
            }
        }
    }

    interface OnCountDownRecycleView{
        fun onItemClick(position: Int)
    }
}