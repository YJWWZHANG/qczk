package com.zqb.qczk.ui.login

import android.content.Context
import android.content.Intent
import android.view.View
import com.zqb.qczk.R
import com.zqb.qczk.base.BaseActivity
import com.zqb.qczk.base.contract.login.RegisterContract
import com.zqb.qczk.presenter.login.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity(override val layoutId: Int = R.layout.activity_register) : BaseActivity<RegisterPresenter>(), RegisterContract.View, View.OnClickListener{
    companion object {

        fun launch(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }
    override fun initInject() {
        getActivityComponent().inject(this)
        mPresenter.attachView(this)
    }

    override fun initEventAndData() {
        tv_protocol.setOnClickListener(this)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_protocol -> {

            }
            else -> {

            }
        }
    }
}