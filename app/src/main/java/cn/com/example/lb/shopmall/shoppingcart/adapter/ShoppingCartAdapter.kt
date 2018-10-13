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
import cn.com.example.lb.shopmall.shoppingcart.utils.CartStorage
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide

class ShoppingCartAdapter(val context:Context,val goodsBeanList:ArrayList<GoodsBean>?,val checkBoxAll:CheckBox,val tv_shopcart_total:TextView,val cb_all:CheckBox):RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    lateinit var itemView:View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemView = View.inflate(context, R.layout.item_shop_cart,null)
        val viewHolder = ViewHolder(itemView)
        return viewHolder
    }

    init {
        showTotalPrice()
        setListener()
        checkAll()
    }

    private fun setListener() {
        setOnItemClickListener(object:OnItemClickListener{
            override fun onItemClickListener(position: Int) {
                val goodsBean = goodsBeanList!![position]
                goodsBean.isSelected = !goodsBean.isSelected
                notifyItemChanged(position)
                checkAll()
                showTotalPrice()
            }
        })
        checkBoxAll.setOnClickListener {
            v: View? ->
            val isChecked = checkBoxAll.isChecked
            checkAll_None(isChecked)
            showTotalPrice()
        }

        cb_all.setOnClickListener {
            v: View? ->
            val isChecked = cb_all.isChecked
            checkAll_None(isChecked)
        }
    }

     fun checkAll_None(checked: Boolean) {
        if(goodsBeanList!!.isNotEmpty()){
            for(i in 0 until goodsBeanList.size){
                val goodsBean = goodsBeanList[i]
                goodsBean.isSelected = checked
                notifyItemChanged(i)
            }
        }
    }

     fun checkAll() {
        if(goodsBeanList!!.isNotEmpty()){
            var number = 0
         for(i in 0 until goodsBeanList.size){
             val goodsBean = goodsBeanList[i]
             if(!goodsBean.isSelected){
                 checkBoxAll.isChecked = false
                 cb_all.isChecked = false
             }else{
                 number ++
             }
             if(number == goodsBeanList.size){
                 checkBoxAll.isChecked = true
                 cb_all.isChecked = true
             }
         }
        }else{
            cb_all.isChecked = false
            checkBoxAll.isChecked = false
        }
    }

    fun showTotalPrice() {
        tv_shopcart_total.text = "合计:${getTotalPrice()}"
    }

    /**
     * 计算总价格
     */
    private fun getTotalPrice(): String {
         var totalPrice = 0.0
        if(goodsBeanList!!.isNotEmpty()){
            for(i in 0 until goodsBeanList.size){
                val goodsBean = goodsBeanList[i]
                if(goodsBean.isSelected){
                    totalPrice += (goodsBean.cover_price.toDouble() * goodsBean.number)
                }
            }
        }
        return totalPrice.toString()
    }

    override fun getItemCount() = goodsBeanList!!.size

    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        val goodsBean = goodsBeanList!![position]
        holder.cb_gov.isChecked = goodsBean.isSelected
        Glide.with(itemView).load(NetUrlUtils.BASE_IMAGE_URL+goodsBean.figure).into(holder.iv_gov)
        holder.tv_desc_gov.text = goodsBean.name
        holder.tv_price_gov.text = "￥${goodsBean.cover_price}"
        holder.numberAddSubView.currentValue = goodsBean.number
        holder.numberAddSubView.maxValue = 10
        holder.numberAddSubView.minValue = 1
        holder.numberAddSubView.setOnNumberValueChangeListener(object : AddSubView.OnNumberValueChangeListener{
            override fun onNumberValueChangeListener(value: Int) {
                   goodsBean.number = value
                   CartStorage.updateData(goodsBean)
                   notifyItemChanged(position)
                   showTotalPrice()
            }
        })
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
            itemView.setOnClickListener {
                v: View? ->
                if(onItemClickListener != null){
                    onItemClickListener?.onItemClickListener(layoutPosition)
                }
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClickListener(position:Int)
    }

    private  var onItemClickListener: OnItemClickListener? = null

    private fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this@ShoppingCartAdapter.onItemClickListener = onItemClickListener
    }

    fun deleteData() {
        if(goodsBeanList!!.isNotEmpty()){
            goodsBeanList.iterator().let {
                while (it.hasNext()){
                    val goodsBean = it.next()
                    if(goodsBean.isSelected){
                        val index = goodsBeanList.indexOf(goodsBean)
                        it.remove()
                        CartStorage.deleteData(goodsBean)
                        notifyItemRemoved(index)
                    }
                }
            }

        }
    }
}