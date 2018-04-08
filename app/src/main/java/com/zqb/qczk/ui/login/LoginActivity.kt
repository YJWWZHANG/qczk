package com.zqb.qczk.ui.login

import android.content.Context
import android.content.Intent
import com.zqb.qczk.R
import com.zqb.qczk.app.App
import com.zqb.qczk.base.SimpleActivity

class LoginActivity(override val layoutId: Int = R.layout.activity_login) : SimpleActivity() {

    companion object {
        fun launch(context : Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun initEventAndData() {

    }

    override fun onBackPressed() {
        App.instance!!.exitApp()
    }
}