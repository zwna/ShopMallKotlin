package com.example.addsubview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.add_sub_view.view.*

class AddSubView:LinearLayout {

     var currentValue:Int = 1
     set(value){
        field = value
        tv_value.text = value.toString()
     }
     get(){
         field = tv_value.text.toString().toInt()
         return field
     }
     var maxValue:Int = 5
     var minValue:Int = 1

    private var onNumberValueChangeListener: OnNumberValueChangeListener? = null

    fun setOnNumberValueChangeListener(onNumberValueChangeListener: OnNumberValueChangeListener){
        this.onNumberValueChangeListener = onNumberValueChangeListener
    }

    constructor(context: Context):super(context){
      init(context)
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
          init(context)
    }

    constructor(context: Context,attributeSet: AttributeSet,defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
        init(context)
    }

    private fun init(context: Context){
          View.inflate(context,R.layout.add_sub_view,this)
          currentValue = currentValue
          iv_sub.setOnClickListener{
              v: View? ->subNumber()
          }
          iv_add.setOnClickListener{
            v: View? -> addNumber()
          }
    }

    private fun monitorOnNumberValueChange(){
        if(onNumberValueChangeListener != null){
            onNumberValueChangeListener?.onNumberValueChangeListener(currentValue)
        }
    }

    private fun subNumber(){
         if(currentValue > minValue){
             currentValue --
         }
        monitorOnNumberValueChange()
    }

    private fun addNumber(){
      if(currentValue < maxValue){
          currentValue ++
      }
        monitorOnNumberValueChange()
    }

    interface OnNumberValueChangeListener{
        fun onNumberValueChangeListener(value:Int)
    }

}