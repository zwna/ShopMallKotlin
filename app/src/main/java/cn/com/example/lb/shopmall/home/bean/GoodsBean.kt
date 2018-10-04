package cn.com.example.lb.shopmall.home.bean

import java.io.Serializable

data class GoodsBean(val cover_price: String,
                 val figure: String,
                 val name: String,
                 val product_id: String):Serializable {

    var number:Int = 1

    override fun toString(): String {
        return "GoodsBean(cover_price='$cover_price', figure='$figure', name='$name', product_id='$product_id', number=$number)"
    }


}