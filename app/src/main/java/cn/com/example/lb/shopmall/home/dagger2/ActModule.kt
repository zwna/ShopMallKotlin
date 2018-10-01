package cn.com.example.lb.shopmall.home.dagger2

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import cn.com.example.lb.shopmall.home.bean.ActInfo
import cn.com.example.lb.shopmall.utils.NetUrlUtils
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides


@Module
class ActModule(val context:Context,val actInfoList:List<ActInfo>) {


    @Provides
    fun providePagerAdapter() = object : PagerAdapter(){
        override fun isViewFromObject(view: View, `object`: Any) = view === `object`

        override fun getCount() = actInfoList.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(context)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setOnClickListener{
                v -> Toast.makeText(context,"position == $position",Toast.LENGTH_SHORT).show()
            }
            Glide.with(imageView).load(NetUrlUtils.BASE_IMAGE_URL + actInfoList[position].icon_url).into(imageView)
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object`as View)
        }

    }
}