package com.zqb.qczk.ui.login

import android.content.Context
import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.zqb.qczk.R
import com.zqb.qczk.app.App
import com.zqb.qczk.app.Constants
import com.zqb.qczk.base.BaseActivity
import com.zqb.qczk.base.contract.LoginContract
import com.zqb.qczk.model.bean.LoginResuleBean
import com.zqb.qczk.presenter.login.LoginPresenter
import com.zqb.qczk.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity(override val layoutId: Int = R.layout.activity_login) : BaseActivity<LoginPresenter>(), LoginContract.View, View.OnClickListener{
    override fun initInject() {
        getActivityComponent().inject(this)
        mPresenter.attachView(this)
    }

    companion object {
        fun launch(context : Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun initEventAndData() {
        btn_login.setOnClickListener(this)
        btn_qq_login.setOnClickListener(this)
        btn_wx_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        tv_forget.setOnClickListener(this)

        val account = SPUtils.getInstance(Constants.SP_CONFIG).getString(Constants.ACCOUNT)
        val password = SPUtils.getInstance(Constants.SP_CONFIG).getString(Constants.PASSWORD)
        if (!StringUtils.isSpace(account)) {
            et_account.setText(account)
        }
        if (!StringUtils.isSpace(password)) {
            et_password.setText(password)
        }

    }

    override fun onBackPressed() {
        App.instance!!.exitApp()
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btn_login -> {
                val account = et_account.text.toString()
                val password = et_password.text.toString()
                if (StringUtils.isSpace(account)){
                    ToastUtils.showShort("请输入用户名")
                    return
                }
                if (StringUtils.isSpace(password)){
                    ToastUtils.showShort("请输入密码")
                    return
                }
                if (!NetworkUtils.isConnected()) {
                    ToastUtils.showShort("网络未连接，请检查网络")
                }
                SPUtils.getInstance(Constants.SP_CONFIG).put(Constants.ACCOUNT, account)
                SPUtils.getInstance(Constants.SP_CONFIG).put(Constants.PASSWORD, password)
                mPresenter.login(account, password)
            }
            R.id.btn_qq_login -> {
                ToastUtils.showShort("QQ登录")
            }
            R.id.btn_wx_login -> {
                ToastUtils.showShort("微信登录")
            }
            R.id.tv_register -> {
                ToastUtils.showShort("注册")
            }
            R.id.tv_forget -> {
                ToastUtils.showShort("忘记密码")
            }
            else -> {

            }
        }
    }

    override fun loginResult(loginResuleBean: LoginResuleBean) {
        when(loginResuleBean.status) {
            "success" -> {
                MainActivity.launch(this)
                finish()
            }
            else -> {

            }
        }
    }
}