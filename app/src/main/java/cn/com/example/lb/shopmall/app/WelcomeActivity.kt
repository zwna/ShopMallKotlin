package cn.com.example.lb.shopmall.app

import android.content.Intent
import android.os.Bundle
import cn.com.example.lb.shopmall.R
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.RxActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class WelcomeActivity : RxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
         Observable.timer(2, TimeUnit.SECONDS).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe {
            startActivity(Intent(WelcomeActivity@ this, MainActivity::class.java))
            finish()
        }
    }
}
