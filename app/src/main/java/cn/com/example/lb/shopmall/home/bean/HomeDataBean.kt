package cn.com.example.lb.shopmall.home.bean

import java.io.Serializable


data class HomeDataBean(
    val code: Int,
    val msg: String,
    val result: Result


) {
    override fun toString(): String {
        return "HomeDataBean(code=$code, msg='$msg', result=$result)"
    }
}

data class Result(
    val act_info: List<ActInfo>,
    val banner_info: List<BannerInfo>,
    val channel_info: List<ChannelInfo>,
    val hot_info: List<HotInfo>,
    val recommend_info: List<RecommendInfo>,
    val seckill_info: SeckillInfo

) {
    override fun toString(): String {
        return "Result(act_info=$act_info, banner_info=$banner_info, channel_info=$channel_info, hot_info=$hot_info, recommend_info=$recommend_info, seckill_info=$seckill_info)"
    }
}


data class RecommendInfo(
    val cover_price: String,
    val figure: String,
    val name: String,
    val product_id: String


):Serializable {
    override fun toString(): String {
        return "RecommendInfo(cover_price='$cover_price', figure='$figure', name='$name', product_id='$product_id')"
    }
}

data class ActInfo(
    val icon_url: String,
    val name: String,
    val url: String


) {
    override fun toString(): String {
        return "ActInfo(icon_url='$icon_url', name='$name', url='$url')"
    }
}

data class ChannelInfo(
    val channel_name: String,
    val image: String,
    val option: Int,
    val type: Int,
    val value: Value1


) {
    override fun toString(): String {
        return "ChannelInfo(channel_name='$channel_name', image='$image', option=$option, type=$type, value=$value)"
    }
}

data class Value1(
    val channel_id: String


) {
    override fun toString(): String {
        return "Value1(channel_id='$channel_id')"
    }
}

data class SeckillInfo(
    val end_time: String,
    val list: List<X>,
    val start_time: String


):Serializable {
    override fun toString(): String {
        return "SeckillInfo(end_time='$end_time', list=$list, start_time='$start_time')"
    }
}

data class X(
    val cover_price: String,
    val figure: String,
    val name: String,
    val origin_price: String,
    val product_id: String


) {
    override fun toString(): String {
        return "X(cover_price='$cover_price', figure='$figure', name='$name', origin_price='$origin_price', product_id='$product_id')"
    }
}

data class BannerInfo(
    val image: String,
    val option: Int,
    val type: Int,
    val value: Value


):Serializable {
    override fun toString(): String {
        return "BannerInfo(image='$image', option=$option, type=$type, value=$value)"
    }
}

data class Value(
    val url: String


) {
    override fun toString(): String {
        return "Value(url='$url')"
    }
}

data class HotInfo(
    val cover_price: String,
    val figure: String,
    val name: String,
    val product_id: String


):Serializable {
    override fun toString(): String {
        return "HotInfo(cover_price='$cover_price', figure='$figure', name='$name', product_id='$product_id')"
    }
}