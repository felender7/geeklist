package co.za.theappbrewery.phonebooktest.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val name:String? = null,
    val phone:String? = null,
    val roleName:String? = null,
    val profileUrl:String?=null
):Parcelable

