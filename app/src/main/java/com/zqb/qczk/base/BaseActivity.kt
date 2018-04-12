package com.zqb.qczk.base

import com.zqb.qczk.di.component.ActivityComponent
import com.zqb.qczk.di.component.DaggerActivityComponent
import com.zqb.qczk.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<T : BasePresenter<*>> : SimpleActivity(), BaseView{

    @Inject
    protected lateinit var mPresenter : T

    override fun onViewCreated() {
        super.onViewCreated()
        initInject()
//        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    fun getActivityComponent() : ActivityComponent {
        return DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build()
    }

    abstract fun initInject()

}
