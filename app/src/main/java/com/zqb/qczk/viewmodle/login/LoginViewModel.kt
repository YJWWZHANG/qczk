package com.zqb.qczk.viewmodle.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zqb.qczk.model.bean.LoginResultBean
import com.zqb.qczk.model.bean.UserBean

class LoginViewModel : ViewModel(){

    private var mUser : MutableLiveData<UserBean>? = null
    private var mLoginResult : LiveData<LoginResultBean>? = null

    fun login() : LiveData<LoginResultBean> {
        if (mLoginResult == null) {
            mLoginResult = MutableLiveData<LoginResultBean>()
        }
        return mLoginResult as LiveData<LoginResultBean>
    }
}