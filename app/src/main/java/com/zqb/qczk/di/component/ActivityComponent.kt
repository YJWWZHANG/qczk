package com.zqb.qczk.di.component

import android.app.Activity
import com.zqb.qczk.di.module.ActivityModule
import com.zqb.qczk.di.scope.ActivityScope
import com.zqb.qczk.ui.login.LoginActivity
import com.zqb.qczk.ui.login.RegisterActivity
import com.zqb.qczk.ui.main.MainActivity


import dagger.Component

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    val activity: Activity

    fun inject(mainActivity: MainActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)

}
