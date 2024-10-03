package com.mistercoding.compak.classes

import android.os.Parcel
import android.os.Parcelable

data class Resources(val image:Int,val name:String,val description:String) : Parcelable {
    constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString()!!,
    parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Resources> {
        override fun createFromParcel(parcel: Parcel): Resources {
            return Resources(parcel)
        }

        override fun newArray(size: Int): Array<Resources?> {
            return arrayOfNulls(size)
        }
    }
}