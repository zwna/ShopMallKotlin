package cn.com.example.lb.shopmall.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.home.bean.BannerInfo
import cn.com.example.lb.shopmall.home.bean.ChannelInfo
import cn.com.example.lb.shopmall.home.bean.Result
import cn.com.example.lb.shopmall.home.dagger2.ChannelModule
import cn.com.example.lb.shopmall.home.dagger2.DaggerChannelComponent
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide
import com.stx.xhb.xbanner.XBanner
import com.stx.xhb.xbanner.transformers.Transformer
import javax.inject.Inject

class HomeFragmentAdapter(val context:Context,val resultBean: Result):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == BANNER){
           return BannerViewHolder(context,layoutInflater.inflate(R.layout.banner_viewpager,null))
        }else if(viewType == CHANNEL){
            return ChannelViewHolder(context, layoutInflater.inflate(R.layout.channel_item,null),resultBean.channel_info)
        }else{
            return BannerViewHolder(context,layoutInflater.inflate(R.layout.banner_viewpager,null))
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == BANNER){
            val bannerViewHolder = holder as BannerViewHolder
            bannerViewHolder.setData(resultBean.banner_info)
        }else if(getItemViewType(position) == CHANNEL){
            val channelViewHolder = holder as ChannelViewHolder
            channelViewHolder.setData()
        }
    }




    /**
     * 当前类型
     */
    private var currentType:Int = BANNER

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    companion object {
        /**
         * 广告条幅类型
         */
        const val BANNER:Int = 0
        /**
         * 频道
         */
        const val CHANNEL:Int = 1
        /**
         * 活动
         */
        const val ACT:Int = 2
        /**
         * 秒杀
         */
        const val COUNTDOWN:Int = 3
        /**
         * 推荐
         */
        const val RECOMMEND:Int = 4
        /**
         * 热卖
         */
        const val HOT:Int = 5
    }


    override fun getItemViewType(position: Int): Int {
        when(position){
            BANNER -> currentType = BANNER
            CHANNEL -> currentType = CHANNEL
            ACT ->currentType = ACT
            COUNTDOWN -> currentType = COUNTDOWN
            RECOMMEND -> currentType = RECOMMEND
            HOT -> currentType = HOT
        }
        return currentType
    }

    inner class BannerViewHolder(val context: Context,val bannerView:View):RecyclerView.ViewHolder(bannerView){

        @BindView(R.id.banner)
        lateinit var banner:XBanner

         init {
             ButterKnife.bind(this@BannerViewHolder,bannerView)
         }

        fun setData(bannerInfoList:List<BannerInfo>){
            val bannerImageList:ArrayList<String> = ArrayList()
            for(bannerInfo in bannerInfoList){
                bannerImageList.add(NetUrlUtils.BASE_IMAGE_URL + bannerInfo.image)
                println("bannerImageList == $bannerImageList")
            }
            banner.setPageTransformer(Transformer.Accordion)
           banner.setData(bannerImageList, null)
            banner.loadImage{
                banner, model, view, position -> Glide.with(bannerView).load(bannerImageList[position]).into(view as ImageView)
            }
            banner.setOnItemClickListener{
                banner, model, view, position ->
                Toast.makeText(context,"position == $position",Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class ChannelViewHolder(val context: Context,val channelView:View,val channelInfoList:List<ChannelInfo>):RecyclerView.ViewHolder(channelView){

        @BindView(R.id.gv_channel)
        lateinit var gv_channel:GridView

        @Inject
        lateinit var channelAdapter: ChannelAdapter

        init {
            ButterKnife.bind(this@ChannelViewHolder,channelView)
            println("channelInfoList == $channelInfoList")
            val channelModule = ChannelModule(context,channelInfoList)
            DaggerChannelComponent.builder().channelModule(channelModule).build().inject(this@ChannelViewHolder)

            gv_channel.setOnItemClickListener{
                parent, view, position, id ->
                Toast.makeText(context,"position == $position",Toast.LENGTH_SHORT).show()
            }
        }

        fun setData(){
            gv_channel.adapter = channelAdapter
        }

    }
}