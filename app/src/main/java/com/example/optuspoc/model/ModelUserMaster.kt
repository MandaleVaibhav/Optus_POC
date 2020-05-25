package com.example.optuspoc.model

import android.os.Parcel
import android.os.Parcelable

/*Model class contains entire API response data model*/
class ModelUserMaster(
    var website: String?,
    var address: UserAddress?,
    var phone: String?,
    var name: String?,
    var company: UserCompany?,
    var id: String?,
    var email: String?,
    var username: String?
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

    override fun writeToParcel(dest: Parcel?, flags: Int) {

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

class UserAddress(
    var zipcode: String,
    var geo: UserGeo,
    var suite: String,
    var city: String,
    var street: String
)

class UserCompany(
    var bs: String,
    var catchPhrase: String,
    var name: String
)

class UserGeo(
    var lng: String,
    var lat: String
)
