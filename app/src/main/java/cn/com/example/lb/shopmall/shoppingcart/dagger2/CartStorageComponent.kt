package cn.com.example.lb.shopmall.shoppingcart.dagger2

import cn.com.example.lb.shopmall.base.ActivityScope
import cn.com.example.lb.shopmall.base.BaseApplicationComponent
import cn.com.example.lb.shopmall.shoppingcart.fragment.ShoppingCartFragment
import cn.com.example.lb.shopmall.shoppingcart.utils.CartStorage
import dagger.Component

@ActivityScope
@Component(modules = [(CartStorageModule::class)],dependencies = [(BaseApplicationComponent::class)])
interface CartStorageComponent {

    fun inject(shoppingCartFragment: ShoppingCartFragment)
}