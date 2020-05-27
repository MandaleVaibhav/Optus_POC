package com.example.optuspoc.model

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.optuspoc.R
import com.squareup.picasso.Picasso

/*@class ModelPhoto will handle response of Photo information*/
class ModelPhoto(
    var albumId: String?,
    var id: String?,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(albumId)
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPhoto> {
        override fun createFromParcel(parcel: Parcel): ModelPhoto {
            return ModelPhoto(parcel)
        }

        override fun newArray(size: Int): Array<ModelPhoto?> {
            return arrayOfNulls(size)
        }
    }

}
@BindingAdapter("loadDetailImage")
fun loadDetailImage(view: ImageView, userDetails: ModelPhoto) {
    Picasso.get().load(userDetails.url).placeholder(R.drawable.ic_icon_background).fit()
        .error(R.drawable.defaultimage).into(view)
}