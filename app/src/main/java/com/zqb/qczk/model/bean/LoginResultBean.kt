package com.zqb.qczk.model.bean
import com.google.gson.annotations.SerializedName

data class LoginResuleBean(
		@SerializedName("status") val status: String = "",
		@SerializedName("message") val message: String = "",
		@SerializedName("obj") val obj: Obj = Obj()
) {

	data class Obj(
			@SerializedName("id") val id: String = "",
			@SerializedName("org") val org: Org = Org(),
			@SerializedName("modifyTime") val modifyTime: String = "",
			@SerializedName("createTime") val createTime: String = "",
			@SerializedName("account") val account: String = "",
			@SerializedName("phone") val phone: String = "",
			@SerializedName("loginType") val loginType: Int = 0,
			@SerializedName("status") val status: Int = 0,
			@SerializedName("sInteract") val sInteract: Int = 0,
			@SerializedName("sAlert") val sAlert: Int = 0,
			@SerializedName("sEmergency") val sEmergency: Int = 0,
			@SerializedName("sRepair") val sRepair: Int = 0,
			@SerializedName("appSid") val appSid: String = "",
			@SerializedName("ownerOrg") val ownerOrg: OwnerOrg = OwnerOrg(),
			@SerializedName("huanxin") val huanxin: String = "",
			@SerializedName("nickName") val nickName: String = "",
			@SerializedName("pushId") val pushId: String = ""
	) {

		data class OwnerOrg(
				@SerializedName("id") val id: String = ""
		)

		class Org

	}

}
