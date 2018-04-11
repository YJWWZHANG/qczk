package com.zqb.qczk.model.bean

class UserBean(var account: String?, var password: String?) {

    override fun toString(): String {
        return "UserBean(account=$account, password=$password)"
    }
}