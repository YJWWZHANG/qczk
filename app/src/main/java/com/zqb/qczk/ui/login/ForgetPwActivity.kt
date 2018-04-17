package com.zqb.qczk.ui.login

import android.content.Context
import android.content.Intent
import com.zqb.qczk.R
import com.zqb.qczk.base.SimpleActivity

class ForgetPwActivity(override val layoutId: Int = R.layout.activity_forget_pw) : SimpleActivity() {

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, ForgetPwActivity::class.java))
        }
    }

    override fun initEventAndData() {

    }

    override fun onBackPressed() {
        finish()
    }
}