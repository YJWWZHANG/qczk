package com.zqb.qczk.di.module

import android.app.Activity
import com.zqb.qczk.di.scope.ActivityScope


import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return mActivity
    }

}