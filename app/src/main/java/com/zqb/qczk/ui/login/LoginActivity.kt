package com.zqb.qczk.ui.login

import android.content.Context
import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.zqb.qczk.R
import com.zqb.qczk.app.App
import com.zqb.qczk.base.SimpleActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity(override val layoutId: Int = R.layout.activity_login) : SimpleActivity() , View.OnClickListener{
    companion object {
        fun launch(context : Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
    override fun initEventAndData() {
        btn_login.setOnClickListener(this)
    }

    override fun onBackPressed() {
        App.instance!!.exitApp()
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btn_login -> {
                val userName = et_account.text.toString()
                val password = et_password.text.toString()
                if (StringUtils.isSpace(userName)){
                    ToastUtils.showShort("请输入用户名")
                    return
                }
                if (StringUtils.isSpace(password)){
                    ToastUtils.showShort("请输入密码")
                    return
                }
            }
            else -> {

            }
        }
    }
}