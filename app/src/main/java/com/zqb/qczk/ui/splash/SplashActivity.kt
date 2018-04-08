package com.zqb.qczk.ui.splash

import android.os.Handler
import com.zqb.qczk.R
import com.zqb.qczk.base.SimpleActivity
import com.zqb.qczk.ui.login.LoginActivity

class SplashActivity(override val layoutId: Int = R.layout.activity_splash) : SimpleActivity() {

    override fun initEventAndData() {
        Handler().postDelayed(Runnable {
            LoginActivity.launch(this)
            finish()
        }, 1000)
    }
}