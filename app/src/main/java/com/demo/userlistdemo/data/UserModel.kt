package com.demo.userlistdemo.data
import com.google.gson.annotations.SerializedName
data class UserModel(

    @SerializedName("login"               ) var login             : String?  = null,
    @SerializedName("id"                  ) var id                : String?     = null,
    @SerializedName("type"                ) var type              : String?  = null,
                 )
