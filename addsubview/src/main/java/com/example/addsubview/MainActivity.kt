package com.example.addsubview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_sub_view.setOnNumberValueChangeListener(object : AddSubView.OnNumberValueChangeListener{
            override fun onNumberValueChangeListener(value: Int) {
                Toast.makeText(this@MainActivity,value.toString(),Toast.LENGTH_SHORT).show()
            }
        })

    }
}
