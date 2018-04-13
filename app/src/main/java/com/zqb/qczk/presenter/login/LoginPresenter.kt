package com.zqb.qczk.presenter.login

import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.zqb.qczk.base.RxPresenter
import com.zqb.qczk.base.contract.login.LoginContract
import com.zqb.qczk.model.bean.LoginResuleBean
import com.zqb.qczk.model.net.QczkRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(): RxPresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(account: String, password: String) {
        QczkRequest.login(account, password)!!
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(Consumer {

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val loginResuleBean = Gson().fromJson(it.body(), LoginResuleBean::class.java)
                    mView!!.loginResult(loginResuleBean)
                }, {
                    ToastUtils.showLong("请求超时，请检查网络")
                }, {

                })
    }


}