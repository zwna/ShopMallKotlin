package cn.com.example.lb.shopmall

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        disableShiftMode(bottomNavigationView)
        main_viewPager.offscreenPageLimit = 5
        main_viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager){
            override fun getItem(position: Int) = when(position){
                    0 -> Fragment()
                    1 -> Fragment()
                    2 -> Fragment()
                    3 -> Fragment()
                    4 -> Fragment()
                    else -> null
                }

            override fun getCount() = 5

        }
    }

    /**
     * 该方法用来取消底部导航栏BottomNavigationView的动画效果
     *
     * @param view 取消动画效果的目标BottomNavigationView
     */
    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView

        try {
            //通过反射获取BottomNavigationMenuView的"mShiftingMode"属性
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            //设置"mShiftingMode"属性可访问和修改
            shiftingMode.isAccessible = true
            //修改"mShiftingMode"属性的值为false
            shiftingMode.setBoolean(menuView, false)
            //再次设置"mShiftingMode"属性可访问和修改
            shiftingMode.isAccessible = false
            //遍历menuView的所有子View
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView

                //将menuView的item的"mShiftingMode"属性设置为false
                item.setShiftingMode(false)
                // set once again checked value, so view will be updated

                //设置menuView的item是否被选中
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BNVHelper", "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e)
        }

    }
}
