package com.zqb.qczk.app

import android.app.Activity
import android.app.Application
import android.os.Process
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.Utils
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * 创建时间:2018/4/7 21:27
 */
class App : Application() {

    private var mAllActivities : MutableSet<Activity> = HashSet()

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)

        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.INFO)
        builder.addInterceptor(loggingInterceptor)
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount = 3

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
