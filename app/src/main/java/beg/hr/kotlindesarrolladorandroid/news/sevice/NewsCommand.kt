package beg.hr.kotlindesarrolladorandroid.news.sevice

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by juraj on 27/02/2017.
 */
data class NewsCommand(val type: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NewsCommand> = object : Parcelable.Creator<NewsCommand> {
            override fun createFromParcel(source: Parcel): NewsCommand = NewsCommand(source)
            override fun newArray(size: Int): Array<NewsCommand?> = arrayOfNulls(size)
        }
        val TYPE_LOAD: String = "load"
        val TYPE_REFRESH: String = "refresh"
    }

    constructor(source: Parcel) : this(source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(type)
    }
}