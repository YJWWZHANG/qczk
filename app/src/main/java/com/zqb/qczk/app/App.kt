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
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.UMShareConfig
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * 创建时间:2018/4/7 21:27
 */
class App : Application() {


    var mAllActivities : MutableSet<Activity> = HashSet()

    init {
        PlatformConfig.setQQZone("1106599959", "gkHDehPzcrLGgaLG")
        PlatformConfig.setSinaWeibo("2205802917", "d563aa7b33258c25c19088433bc884af", "http://sns.whalecloud.com")

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0")
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf")
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO")
        PlatformConfig.setAlipay("2015111700822536")
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e")
        PlatformConfig.setPinterest("1439206")
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f")
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk")
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J")
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a")
    }

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

        UMConfigure.setLogEnabled(true)
        UMConfigure.init(this,"5ad599ce8f4a9d6aae00005f","umeng",UMConfigure.DEVICE_TYPE_PHONE,"")
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
