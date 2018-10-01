package cn.com.example.lb.shopmall.app

import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.ViewGroup
import butterknife.ButterKnife
import cn.com.example.lb.shopmall.R
import cn.com.example.lb.shopmall.community.fragment.CommunityFragment
import cn.com.example.lb.shopmall.home.fragment.HomeFragment
import cn.com.example.lb.shopmall.shoppingcart.fragment.ShoppingCartFragment
import cn.com.example.lb.shopmall.type.fragment.TypeFragment
import cn.com.example.lb.shopmall.user.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : FragmentActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var communityFragment: CommunityFragment
    private lateinit var shoppingCartFragment: ShoppingCartFragment
    private lateinit var typeFragment: TypeFragment
    private lateinit var userFragment: UserFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        disableShiftMode(bottomNavigationView)
        homeFragment = HomeFragment()
        communityFragment = CommunityFragment()
        shoppingCartFragment = ShoppingCartFragment()
        typeFragment = TypeFragment()
        userFragment = UserFragment()
        main_viewPager.offscreenPageLimit = 5
        main_viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager){
            override fun getItem(position: Int) = when(position){
                    0 -> homeFragment
                    1 -> communityFragment
                    2 -> shoppingCartFragment
                    3 -> typeFragment
                    4 -> userFragment
                    else -> null
                }

            override fun getCount() = 5

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//                super.destroyItem(container, position, `object`)
            }

        }
        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            item ->
                when (item.itemId) {
                    R.id.home_page -> main_viewPager.currentItem = 0
                    R.id.kind -> main_viewPager.currentItem = 1
                    R.id.find -> main_viewPager.currentItem = 2
                    R.id.buy_car -> main_viewPager.currentItem = 3
                    R.id.user_center -> main_viewPager.currentItem = 4
                    else ->main_viewPager.currentItem = 0
                }
            true
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
