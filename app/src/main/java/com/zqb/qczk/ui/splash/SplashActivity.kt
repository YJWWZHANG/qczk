package com.zqb.qczk.ui.splash

import android.os.Handler
import com.blankj.utilcode.util.ToastUtils
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import com.zqb.qczk.R
import com.zqb.qczk.base.SimpleActivity
import com.zqb.qczk.ui.login.LoginActivity


class SplashActivity(override val layoutId: Int = R.layout.activity_splash) : SimpleActivity() {

    override fun initEventAndData() {
        Handler().postDelayed(Runnable {
            AndPermission.with(this)
                    .permission(Permission.Group.STORAGE)
                    .onGranted {
                        LoginActivity.launch(this)
                        finish()
                    }.onDenied({
                        ToastUtils.showShort("为使应用各功能正常使用，请允许我们申请的权限")
                    })
                    .start()
        }, 1000)
    }
}