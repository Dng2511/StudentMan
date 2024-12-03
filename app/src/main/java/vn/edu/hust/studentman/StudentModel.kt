package vn.edu.hust.studentman

import android.os.Parcel
import android.os.Parcelable

data class StudentModel(
    val name: String?,
    val id: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StudentModel> {
        override fun createFromParcel(parcel: Parcel): StudentModel {
            return StudentModel(parcel)
        }

        override fun newArray(size: Int): Array<StudentModel?> {
            return arrayOfNulls(size)
        }
    }
}

