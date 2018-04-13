package com.zqb.qczk.base.contract.login

import com.zqb.qczk.base.BasePresenter
import com.zqb.qczk.base.BaseView

interface RegisterContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }
}