package com.zqb.qczk.base.contract

import com.zqb.qczk.base.BasePresenter
import com.zqb.qczk.base.BaseView
import com.zqb.qczk.model.bean.LoginResuleBean

interface LoginContract {

    interface View : BaseView {
        fun loginResult(loginResuleBean: LoginResuleBean)
    }

    interface Presenter : BasePresenter<View> {
        fun login(account : String, password : String)
    }
}