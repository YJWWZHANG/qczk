package com.zqb.qczk.ui.login

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.blankj.utilcode.util.*
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.zqb.qczk.R
import com.zqb.qczk.app.App
import com.zqb.qczk.app.Constants
import com.zqb.qczk.base.BaseActivity
import com.zqb.qczk.base.contract.login.LoginContract
import com.zqb.qczk.di.qualifier.LoadingDialog
import com.zqb.qczk.di.qualifier.LoginDialog
import com.zqb.qczk.model.bean.LoginResuleBean
import com.zqb.qczk.presenter.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity(override val layoutId: Int = R.layout.activity_login) : BaseActivity<LoginPresenter>(), LoginContract.View, View.OnClickListener{

    var mAccount : String = ""
    var mPassword : String = ""
    @Inject @field:LoginDialog
    lateinit var mLoadingDialog : Dialog

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

//        mLoadingDialog = mCBDialogBuilder.setMessage("登录").create()

        et_account.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length == 11 && !RegexUtils.isMobileExact(s)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_account)
                    ToastUtils.showShort("请检查输入的手机号")
                }
            }
        })

        mAccount = SPUtils.getInstance(Constants.SP_CONFIG).getString(Constants.ACCOUNT)
        mPassword = SPUtils.getInstance(Constants.SP_CONFIG).getString(Constants.PASSWORD)
        if (!StringUtils.isSpace(mAccount)) {
            et_account.setText(mAccount)
        }
        if (!StringUtils.isSpace(mPassword)) {
            et_password.setText(mPassword)
        }

    }

    override fun onBackPressed() {
        App.instance!!.exitApp()
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btn_login -> {
                mAccount = et_account.text.toString()
                mPassword = et_password.text.toString()
                if (StringUtils.isSpace(mAccount)){
                    ToastUtils.showShort("手机号不能为空")
                    return
                }
                if (StringUtils.isSpace(mPassword)){
                    ToastUtils.showShort("密码不能为空")
                    return
                }
                if (!RegexUtils.isMobileExact(mAccount)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(et_account)
                    ToastUtils.showShort("手机号有误，请检查输入的手机号")
                    return
                }
                if (!NetworkUtils.isConnected()) {
                    ToastUtils.showShort("网络未连接，请检查网络")
                    return
                }
                SPUtils.getInstance(Constants.SP_CONFIG).put(Constants.ACCOUNT, mAccount)
                SPUtils.getInstance(Constants.SP_CONFIG).put(Constants.PASSWORD, mPassword)
                mPresenter.login(mAccount, mPassword)
            }
            R.id.btn_qq_login -> {
                ToastUtils.showShort("QQ登录")
            }
            R.id.btn_wx_login -> {
                ToastUtils.showShort("微信登录")
            }
            R.id.tv_register -> {
                RegisterActivity.launch(this)
            }
            R.id.tv_forget -> {
                ToastUtils.showShort("忘记密码")
            }
            else -> {

            }
        }
    }

    override fun loginResult(loginResultBean: LoginResuleBean) {
        when(loginResultBean.status) {
            "success" -> {
                ToastUtils.showShort(loginResultBean.message)
//                MainActivity.launch(this)
//                finish()
            }
            "error" -> {
                ToastUtils.showShort(loginResultBean.message)
            }
            else -> {

            }
        }
    }

    override fun showDialog() {
        mLoadingDialog.show()
    }

    override fun dimissDialog() {
        mLoadingDialog.dismiss()
    }

}