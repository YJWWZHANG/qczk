package com.zqb.qczk.app

import android.app.Activity
import android.app.Application
import android.os.Process
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.Utils

/**
 * 创建时间:2018/4/7 21:27
 */
class App : Application() {

    private var mAllActivities : MutableSet<Activity> = HashSet()

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
    }

    fun addActivity(activity: Activity) {
        mAllActivities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        mAllActivities.remove(activity)
    }

    fun exitApp() {
        synchronized(mAllActivities) {
            for (activity in mAllActivities) {
                activity.finish()
            }
            Process.killProcess(Process.myPid())
            System.exit(0)
        }
    }

    companion object {
        var instance : App? = null
    }
}
