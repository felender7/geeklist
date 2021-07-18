package co.za.theappbrewery.geeklist.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    var name: String,
    var phone:String,
    val roleName: String,
    val profileUrl: String
): Parcelable