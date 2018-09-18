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
import cn.com.example.lb.shopmall.home.bean.ChannelInfo
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide

class ChannelAdapter(val context: Context,val channelInfoList:List<ChannelInfo>):BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var cacheView:View? = convertView
        var viewHolder:ViewHolder? = null
        if(cacheView == null){
            cacheView = View.inflate(context, R.layout.item_channel,null)
            viewHolder = ViewHolder(cacheView)
            cacheView?.tag = viewHolder
        }else{
            viewHolder = cacheView.tag as ViewHolder
        }

        val channelInfo = channelInfoList[position]
        Glide.with(cacheView!!).load(NetUrlUtils.BASE_IMAGE_URL + channelInfo.image).into(viewHolder.iv_channel)
        viewHolder.tv_channel.text = channelInfo.channel_name
        return cacheView
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int) = 0L

    override fun getCount() = channelInfoList.size

    class ViewHolder(private val itemView:View){

        @BindView(R.id.iv_channel)
        lateinit var iv_channel:ImageView
        @BindView(R.id.tv_channel)
        lateinit var tv_channel:TextView

        init {
            ButterKnife.bind(this@ViewHolder,itemView)
        }
    }

}