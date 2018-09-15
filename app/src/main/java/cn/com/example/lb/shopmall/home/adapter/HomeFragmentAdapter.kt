package cn.com.example.lb.shopmall.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class HomeFragmentAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * 当前类型
     */
    private var currentType:Int = BANNER

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
}