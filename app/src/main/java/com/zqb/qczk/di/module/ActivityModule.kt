package com.zqb.qczk.di.module


import android.app.Activity
import android.app.Dialog
import com.zhl.cbdialog.CBDialogBuilder
import com.zqb.qczk.R
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


    @Provides
    fun provideLoginDialog(): Dialog {
        return CBDialogBuilder(mActivity, R.layout.cb_dialog_progress_avloading, 0.6f).setMessage("登录中...").create()
    }

//    @Provides
//    @Named("loading_dialog")
//    fun provideLoadingDialog(): Dialog {
//        return CBDialogBuilder(mActivity, R.layout.cb_dialog_progress_avloading, 0.6f).setMessage("加载中...").create()
//    }

}