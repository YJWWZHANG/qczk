package com.zqb.qczk.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zqb.qczk.R
import com.zqb.qczk.app.App

class MainActivity : AppCompatActivity() {

    companion object {
        fun launch(context : Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        App.instance!!.exitApp()
    }
}
