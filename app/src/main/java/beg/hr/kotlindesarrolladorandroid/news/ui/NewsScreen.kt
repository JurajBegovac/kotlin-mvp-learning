package beg.hr.kotlindesarrolladorandroid.news.ui

import android.os.Parcel
import android.os.Parcelable
import beg.hr.kotlindesarrolladorandroid.common.Presenter

/**
 * Created by juraj on 23/02/2017.
 */
fun defaultViewState(): NewsScreen.ViewState = NewsScreen.ViewState(true, emptyList())

class NewsScreen() : Parcelable {
    interface View {
        fun render(state: ViewState)
    }

    interface NewsPresenter : Presenter {
    }

    // View Models
    data class ViewState(val loading: Boolean, val news: List<NewsItem>) : Parcelable {

        companion object {
            @JvmField val CREATOR: Parcelable.Creator<ViewState> = object : Parcelable.Creator<ViewState> {
                override fun createFromParcel(source: Parcel): ViewState = ViewState(source)
                override fun newArray(size: Int): Array<ViewState?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel) : this(1.equals(source.readInt()), source.createTypedArrayList(NewsItem.CREATOR))

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeInt((if (loading) 1 else 0))
            dest?.writeTypedList(news)
        }
    }

    data class NewsItem(val title: String, val imgUrl: String) : Parcelable {
        companion object {
            @JvmField val CREATOR: Parcelable.Creator<NewsItem> = object : Parcelable.Creator<NewsItem> {
                override fun createFromParcel(source: Parcel): NewsItem = NewsItem(source)
                override fun newArray(size: Int): Array<NewsItem?> = arrayOfNulls(size)
            }
        }

        constructor(source: Parcel) : this(source.readString(), source.readString())

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(title)
            dest?.writeString(imgUrl)
        }
    }

    // this is parcelable for screen
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NewsScreen> = object : Parcelable.Creator<NewsScreen> {
            override fun createFromParcel(source: Parcel): NewsScreen = NewsScreen(source)
            override fun newArray(size: Int): Array<NewsScreen?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this()

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {}
}
