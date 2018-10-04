package cn.com.example.lb.shopmall.shoppingcart.utils

import android.text.TextUtils
import android.util.SparseArray
import cn.com.example.lb.shopmall.home.bean.GoodsBean
import cn.com.example.lb.shopmall.utils.CacheUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object CartStorage {

    const val jsonCart:String = "json_cart"

    private var sparseArray: SparseArray<GoodsBean> = SparseArray(100)

    init {
        listToSparseArray()
    }

    private fun listToSparseArray(){
        val goodsBeanList:List<GoodsBean>? = getAllData()
        if(goodsBeanList != null) {
            for (goodsBean in goodsBeanList) {
                if(goodsBean != null) {
                    sparseArray.put(goodsBean.product_id.toInt(), goodsBean)
                }
            }
        }
    }

    fun getAllData(): List<GoodsBean> {
         var goodsBeanList:ArrayList<GoodsBean> = ArrayList()
         val json:String?  = CacheUtils.getString(jsonCart)
        if(!TextUtils.isEmpty(json)) {
            goodsBeanList = Gson().fromJson(json, object : TypeToken<List<GoodsBean>>() {}.type)
            for(goods in goodsBeanList){
                if(goods == null){
                    goodsBeanList.remove(goods)
                }
            }
        }
         return goodsBeanList
    }

    fun addData(goodsBean: GoodsBean){
       var tempData:GoodsBean? = sparseArray[goodsBean.product_id.toInt()]
        if(tempData != null){
           tempData.number = tempData.number + 1
        }else{
            tempData = goodsBean
        }
        sparseArray.put(tempData.product_id.toInt(),tempData)

        saveLocal()

    }

    fun deleteData(goodsBean: GoodsBean){
        sparseArray.delete(goodsBean.product_id.toInt())
        saveLocal()
    }

    fun updateData(goodsBean: GoodsBean){
        sparseArray.put(goodsBean.product_id.toInt(),goodsBean)
        saveLocal()
    }

    private fun saveLocal() {
           val goodsBeanList:List<GoodsBean> = sparseToList()
        val json = Gson().toJson(goodsBeanList)
        CacheUtils.saveString(jsonCart,json)
    }

    private fun sparseToList(): List<GoodsBean> {
        var goodsBeanList:ArrayList<GoodsBean> = ArrayList()
        println("____________" + 0.rangeTo(sparseArray.size()))
         for(i in 0 until sparseArray.size()){
             val goodsBean = sparseArray.valueAt(i)
              goodsBeanList.add(goodsBean)
         }
        return goodsBeanList
        }

}