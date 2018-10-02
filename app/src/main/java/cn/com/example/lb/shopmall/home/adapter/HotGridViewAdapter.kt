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
import cn.com.example.lb.shopmall.home.bean.HotInfo
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide

class HotGridViewAdapter(val context: Context,val hotInfoList:List<HotInfo>):BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var cacheView:View? = p1
        val viewHolder:ViewHolder
        if(cacheView == null) {
           cacheView = View.inflate(context, R.layout.item_hot_grid_view,null)
           viewHolder = ViewHolder(cacheView)
            cacheView.tag = viewHolder
        }else{
            viewHolder = cacheView.tag as ViewHolder
        }

        val hotInfo = hotInfoList[p0]
        Glide.with(cacheView!!).load(NetUrlUtils.BASE_IMAGE_URL + hotInfo.figure).into(viewHolder.iv_hot)
        viewHolder.tv_name.text = hotInfo.name
        viewHolder.tv_price.text = "￥${hotInfo.cover_price}"
        return cacheView

    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getCount() = hotInfoList.size

    class ViewHolder(itemView: View) {

        @BindView(R.id.iv_hot)
        lateinit var iv_hot:ImageView

        @BindView(R.id.tv_name)
        lateinit var tv_name:TextView

        @BindView(R.id.tv_price)
        lateinit var tv_price:TextView


        init {
            ButterKnife.bind(this@ViewHolder, itemView)
        }
    }

}