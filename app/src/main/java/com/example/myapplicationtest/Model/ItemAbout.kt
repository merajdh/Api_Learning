package com.example.myapplicationtest.Model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemAbout (
    var Web : String? = "No_Data",
    var Twt : String? = "No_Data",
    var Reddit : String? = "No_Data",
    var Desc : String? = "No_Data",
    var gitHub : String? = "No_Data",
        ) : Parcelable {

}
