package com.example.optuspoc.model

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.optuspoc.R
import com.squareup.picasso.Picasso

/*Model class contains entire API response data model*/
class ModelUserMaster(
    var website: String?,
    var address: UserAddress?,
    var phone: String?,
    var name: String?,
    var company: UserCompany?,
    var id: String?,
    var email: String?,
    var username: String?,
    var error: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("address"),
        parcel.readString(),
        parcel.readString(),
        TODO("company"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ModelUserMaster> {
        override fun createFromParcel(parcel: Parcel): ModelUserMaster {
            return ModelUserMaster(parcel)
        }

        override fun newArray(size: Int): Array<ModelUserMaster?> {
            return arrayOfNulls(size)
        }
    }
}


@BindingAdapter("loadAlbumImage")
fun loadAlbumImage(
    view: ImageView,
    albumInformationItem: ModelPhoto
) { // This methods should not have any return type, = declaration would make it return that object declaration.
    Picasso.get().load(albumInformationItem.thumbnailUrl).placeholder(R.drawable.ic_icon_background)
        .fit().error(
        R.drawable.defaultimage
    ).into(view)
}

