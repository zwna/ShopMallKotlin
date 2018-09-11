package cn.com.example.lb.shopmall

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
       Observable.timer(2,TimeUnit.SECONDS).subscribe(Consumer {
           startActivity(Intent(WelcomeActivity@this,MainActivity::class.java))
           finish()
       })
    }
}
