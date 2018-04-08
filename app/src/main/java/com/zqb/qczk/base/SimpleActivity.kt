package com.zqb.qczk.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.zqb.qczk.app.App
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class SimpleActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //        Window window = getWindow();
        //        WindowManager.LayoutParams params = window.getAttributes();
        //        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE;
        //        window.setAttributes(params);
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(layoutId)
        onViewCreated()
        App.instance!!.addActivity(this)
        EventBus.getDefault().register(this)
        initEventAndData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance!!.removeActivity(this)
        EventBus.getDefault().unregister(this)
    }

    protected fun onViewCreated() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEventNothing(s: String) {

    }

    protected abstract fun initEventAndData()
}
