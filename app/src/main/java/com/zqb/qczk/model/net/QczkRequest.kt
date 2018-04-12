package com.zqb.qczk.model.net

import com.lzy.okgo.OkGo
import com.lzy.okgo.convert.StringConvert
import com.lzy.okgo.model.Response
import com.lzy.okrx2.adapter.ObservableResponse
import io.reactivex.Observable

class QczkRequest {

    companion object {
        val MAIN_API = "http://yhapp.hp888.com:9091"// 主域名

        fun login(account : String, password : String): Observable<Response<String>>? {
            return OkGo.post<String>(MAIN_API + "/sysapp/applogin/member.json")
                    .tag(this)
                    .params("account", account)
                    .params("password", password)
                    .converter(StringConvert())
                    .adapt(ObservableResponse<String>())
        }
    }
}