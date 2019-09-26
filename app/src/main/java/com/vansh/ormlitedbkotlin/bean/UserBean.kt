package com.vansh.ormlitedbkotlin.bean

import android.os.Parcel
import android.os.Parcelable
import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.vansh.ormlitedbkotlin.database.DatabaseHelper

/**
 * Created by Ganesh Junghare on 9/26/2019.
 */
@DatabaseTable(tableName = DatabaseHelper.USER_TABLE)
class UserBean(): Parcelable {

    @DatabaseField(
        canBeNull = false,
        columnName = AUTO_ID,
        dataType = DataType.INTEGER,
        generatedId = true
    )
    var autoId: Int = 0

    @DatabaseField(canBeNull = false, columnName = USER_NAME, dataType = DataType.STRING)
    var userName: String? = null

    @DatabaseField(canBeNull = false, columnName = USER_MOBILE, dataType = DataType.STRING)
    var userMobile: String? = null

    constructor(parcel: Parcel) : this() {
        autoId = parcel.readInt()
        userName = parcel.readString()
        userMobile = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(autoId)
        parcel.writeString(userName)
        parcel.writeString(userMobile)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserBean> {

        const val AUTO_ID = "autoId"
        const val USER_NAME = "userName"
        const val USER_MOBILE = "userMobile"

        override fun createFromParcel(parcel: Parcel): UserBean {
            return UserBean(parcel)
        }

        override fun newArray(size: Int): Array<UserBean?> {
            return arrayOfNulls(size)
        }
    }
}