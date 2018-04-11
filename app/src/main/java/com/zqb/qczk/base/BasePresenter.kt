package com.zqb.qczk.base

interface BasePresenter<T : BaseView> {

    fun attachView(view: T)
    fun detachView()
}