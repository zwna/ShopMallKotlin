package cn.com.example.lb.shopmall.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.app.ShopMallApplication
import cn.com.example.lb.shopmall.home.bean.*
import cn.com.example.lb.shopmall.home.dagger2.*
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide
import com.stx.xhb.xbanner.XBanner
import com.stx.xhb.xbanner.transformers.Transformer
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import com.zhy.magicviewpager.transformer.ScaleInTransformer
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import javax.inject.Inject

class HomeFragmentAdapter(val context:Context,val resultBean: Result):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BANNER -> BannerViewHolder(context,layoutInflater.inflate(R.layout.banner_viewpager,null))
            CHANNEL -> ChannelViewHolder(context, layoutInflater.inflate(R.layout.channel_item,null),resultBean.channel_info)
            ACT -> ActViewHolder(context,layoutInflater.inflate(R.layout.act_item,null),resultBean.act_info)
            COUNTDOWN -> CountDownViewHolder(context,layoutInflater.inflate(R.layout.seckill_item,null),resultBean.seckill_info)
            RECOMMEND -> RecommendViewHolder(context,layoutInflater.inflate(R.layout.recommend_item,null),resultBean.recommend_info)
            HOT -> HotViewHolder(context,layoutInflater.inflate(R.layout.recommend_item,null),resultBean.hot_info)
            else -> BannerViewHolder(context,layoutInflater.inflate(R.layout.banner_viewpager,null))
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == BANNER -> {
                val bannerViewHolder = holder as BannerViewHolder
                bannerViewHolder.setData(resultBean.banner_info)
            }
            getItemViewType(position) == CHANNEL -> {
                val channelViewHolder = holder as ChannelViewHolder
                channelViewHolder.setData()
            }
            getItemViewType(position) == ACT -> {
                val actViewHolder = holder as ActViewHolder
                actViewHolder.setData()
            }
            getItemViewType(position) == COUNTDOWN -> {
                val countDownViewHolder = holder as CountDownViewHolder
                countDownViewHolder.setData()
            }
            getItemViewType(position) == RECOMMEND -> {
                val recommendViewHolder = holder as RecommendViewHolder
                recommendViewHolder.setData()
            }
            getItemViewType(position) == HOT -> {
                val hotViewHolder = holder as HotViewHolder
                hotViewHolder.setData()
            }
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

    /**
     * 相差多少时间 单位毫秒
     */
    private var dt:Long = 0L


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

    inner class ActViewHolder(val context: Context,actView:View,actInfoList:List<ActInfo>):RecyclerView.ViewHolder(actView){

        @BindView(R.id.act_viewpager)
        lateinit var act_viewpager:ViewPager

        @Inject
        lateinit var pagerAdapter: PagerAdapter

        init {
            ButterKnife.bind(this@ActViewHolder,actView)
            val actModule = ActModule(context,actInfoList)
            DaggerActComponent.builder().actModule(actModule).build().inject(this@ActViewHolder)
        }

        fun setData(){
          act_viewpager.pageMargin = 80
            act_viewpager.offscreenPageLimit = 3
            act_viewpager.setPageTransformer(true,ScaleInTransformer())
          act_viewpager.adapter = pagerAdapter
        }

    }

    inner class CountDownViewHolder(val context: Context,val countDownView:View,val seckillInfo:SeckillInfo):RecyclerView.ViewHolder(countDownView){

        @BindView(R.id.tv_time_seckill)
        lateinit var tv_time_seckill:TextView

        @BindView(R.id.tv_more_seckill)
        lateinit var tv_more_seckill:TextView

        @BindView(R.id.rv_seckill)
        lateinit var rv_seckill:RecyclerView

        @Inject
        lateinit var countDownRecyclerViewAdapter: CountDownRecyclerViewAdapter

        init {
            ButterKnife.bind(this@CountDownViewHolder,countDownView)
            val countDownModule = CountDownModule(context,seckillInfo.list)
            DaggerCountDownComponent.builder().countDownModule(countDownModule).build().inject(this@CountDownViewHolder)
        }

        @SuppressLint("CheckResult")
        fun setData(){
            rv_seckill.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            rv_seckill.adapter = countDownRecyclerViewAdapter
            countDownRecyclerViewAdapter.onCountDownRecycleView = object : CountDownRecyclerViewAdapter.OnCountDownRecycleView{
                override fun onItemClick(position: Int) {
                    Toast.makeText(context,"$position",Toast.LENGTH_SHORT).show()
                }
            }
            dt = seckillInfo.end_time.toLong() - seckillInfo.start_time.toLong()
            Observable.interval(0,1,TimeUnit.SECONDS).compose(RxLifecycleAndroid.bindView(itemView)).take((dt / 1000) + 1).observeOn(AndroidSchedulers.mainThread()).subscribe({
                t: Long? ->
                dt -= 1000
                val format = SimpleDateFormat("HH:mm:ss")
                tv_time_seckill.text = format.format(Date(dt))
            },{e -> System.out.println(e.message)})
        }
    }

    inner class RecommendViewHolder(val context: Context,recommendView:View,recommendInfoList:List<RecommendInfo>):RecyclerView.ViewHolder(recommendView){

        @BindView(R.id.gv_recommend)
        lateinit var gv_recommend:GridView

        @BindView(R.id.tv_more_recommend)
        lateinit var tv_more_recommend:TextView

        @Inject
        lateinit var adapter:RecommendGridViewAdapter

        init {
            ButterKnife.bind(this@RecommendViewHolder,itemView)
            val recommendModule = RecommendModule(context, recommendInfoList)
            DaggerRecommendComponent.builder().recommendModule(recommendModule).build().inject(this@RecommendViewHolder)
        }

        fun setData(){
            gv_recommend.adapter = adapter
        }
    }

    inner class HotViewHolder(val context: Context,hotView:View,hotInfoList:List<HotInfo>):RecyclerView.ViewHolder(hotView){


        init {
            ButterKnife.bind(this@HotViewHolder,hotView)
        }

        fun setData(){

        }
    }
}