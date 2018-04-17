package com.zqb.qczk.di.module


import android.app.Activity
import android.app.Dialog
import com.zhl.cbdialog.CBDialogBuilder
import com.zqb.qczk.R
import com.zqb.qczk.di.scope.ActivityScope
import com.zqb.qczk.di.qualifier.LoadingDialog
import com.zqb.qczk.di.qualifier.LoginDialog
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity = mActivity

    @Provides
    @LoginDialog
    fun provideLoginDialog(): Dialog
            = CBDialogBuilder(mActivity, CBDialogBuilder.DIALOG_STYLE_PROGRESS, 0.6f)
            .setDialogBackground(R.color.material_black50).setMessage("登录中...").create()

    @Provides
    @LoadingDialog
    fun provideLoadingDialog(): Dialog
            = CBDialogBuilder(mActivity, CBDialogBuilder.DIALOG_STYLE_PROGRESS, 0.6f)
            .setDialogBackground(R.color.material_black50).setMessage("加载中...").create()

}