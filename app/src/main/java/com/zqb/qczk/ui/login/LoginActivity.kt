package com.zqb.qczk.ui.login

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.blankj.utilcode.util.*
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA
import com.zqb.qczk.R
import com.zqb.qczk.app.App
import com.zqb.qczk.app.Constants
import com.zqb.qczk.base.BaseActivity
import com.zqb.qczk.base.contract.login.LoginContract
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

    var mAuthListener: UMAuthListener = object : UMAuthListener {
        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        override fun onComplete(platform: SHARE_MEDIA?, action: Int, data: MutableMap<String, String>?) {
            ToastUtils.showShort(data.toString())
            LogUtils.w(data.toString())
        }
        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        override fun onCancel(platform: SHARE_MEDIA?, action: Int) {
//            ToastUtils.showShort("取消登录")
        }
        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        override fun onError(platform: SHARE_MEDIA?, action: Int, t: Throwable?) {
//            ToastUtils.showShort("登录失败")
        }
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        override fun onStart(platform: SHARE_MEDIA?) {
//            ToastUtils.showShort("登录")
        }
    }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
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
                mPresenter.login(mAccount, mPassword)
            }
            R.id.btn_qq_login -> {
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, mAuthListener)
//                UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.QQ, mAuthListener)
            }
            R.id.btn_wx_login -> {
                UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, mAuthListener)
            }
            R.id.tv_register -> {
                RegisterActivity.launch(this)
            }
            R.id.tv_forget -> {
                ForgetPwActivity.launch(this)
            }
            else -> {

            }
        }
    }

    override fun loginResult(loginResultBean: LoginResuleBean) {
        when(loginResultBean.status) {
            "success" -> {
                SPUtils.getInstance(Constants.SP_CONFIG).put(Constants.ACCOUNT, mAccount)
                SPUtils.getInstance(Constants.SP_CONFIG).put(Constants.PASSWORD, mPassword)
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