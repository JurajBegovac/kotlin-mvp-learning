package beg.hr.kotlindesarrolladorandroid.common.ui

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by juraj on 24/02/2017.
 */
data class UserActionEvent(val origin: String, val type: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<UserActionEvent> = object : Parcelable.Creator<UserActionEvent> {
            override fun createFromParcel(source: Parcel): UserActionEvent = UserActionEvent(source)
            override fun newArray(size: Int): Array<UserActionEvent?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(origin)
        dest?.writeString(type)
    }
}