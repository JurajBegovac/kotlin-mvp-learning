package beg.hr.kotlindesarrolladorandroid.news.ui

import android.os.Parcel
import android.os.Parcelable
import beg.hr.kotlindesarrolladorandroid.common.ui.UserActionEvent
import beg.hr.kotlindesarrolladorandroid.common.ui.ViewPresenter
import rx.Observable

/**
 * Created by juraj on 23/02/2017.
 */

/**
 * Screen key - used by saving screen history with flow
 */
class NewsKey() : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NewsKey> = object : Parcelable.Creator<NewsKey> {
            override fun createFromParcel(source: Parcel): NewsKey = NewsKey(source)
            override fun newArray(size: Int): Array<NewsKey?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this()

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {}
}

/**
 * View
 */
interface NewsView {
    fun render(state: State)

    fun userActions(): Observable<UserActionEvent>
}

/**
 * Presenter
 */
abstract class NewsPresenter : ViewPresenter<NewsView>() {
}

class ActionTypes {
    companion object {
        val BASE: String = "view:news"
        val BUTTON_CLICKED: String = BASE + "button:clicked"
    }
}


// View Models
data class State(val loading: Boolean, val news: List<NewsItem>) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<State> = object : Parcelable.Creator<State> {
            override fun createFromParcel(source: Parcel): State = State(source)
            override fun newArray(size: Int): Array<State?> = arrayOfNulls(size)
        }

        val DEFAULT_STATE: State = State(true, emptyList())
        val KEY: String = "key_news_view_state"
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
