package cn.com.example.lb.shopmall.home.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.home.bean.RecommendInfo
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide

class RecommendGridViewAdapter(val context: Context,val recommendInfoList:List<RecommendInfo>):BaseAdapter() {


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val viewHolder:RecommendGridViewAdapter.ViewHolder
        var cacheView:View? = p1
       if(cacheView == null){
           cacheView = View.inflate(context, R.layout.item_recommend_grid_view,null)
           viewHolder = ViewHolder(cacheView)
           cacheView?.tag = viewHolder
       }else{
           viewHolder = cacheView.tag as ViewHolder
       }

        val recommendInfo = recommendInfoList[p0]
        Glide.with(cacheView!!).load(NetUrlUtils.BASE_IMAGE_URL + recommendInfo.figure).into(viewHolder.iv_recommend)
        viewHolder.tv_name.text = recommendInfo.name
        viewHolder.tv_price.text = "￥${recommendInfo.cover_price}"

        return cacheView
    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getCount() = recommendInfoList.size

    class ViewHolder(itemView:View){

        @BindView(R.id.iv_recommend)
        lateinit var iv_recommend:ImageView

        @BindView(R.id.tv_name)
        lateinit var tv_name:TextView

        @BindView(R.id.tv_price)
        lateinit var tv_price:TextView
        init {
            ButterKnife.bind(this@ViewHolder,itemView)
        }
    }

}