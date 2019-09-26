package com.tratagroup.ormlitedatabase.database.daohelper

import android.content.Context
import com.tratagroup.ormlitedatabase.R
import com.tratagroup.ormlitedatabase.bean.UserBean
import com.tratagroup.ormlitedatabase.database.DatabaseHelper
import java.sql.SQLException

/**
 * Created by Ganesh Junghare on 9/26/2019.
 */
class UserDao(val context: Context) {

    @Throws(Exception::class)
    fun createRecord(userBean: UserBean) : Boolean {
        var isCreatedRecord = false
        try {
            val helperUtil = helperUtil()
            val userDao = helperUtil.getUserDao()
            userDao?.create(userBean)
            isCreatedRecord = true
            helperUtil().closeDatabase()
        } catch (e: SQLException) {
            throw Exception(e.message)
        }
       return isCreatedRecord
    }

    private fun helperUtil(): DatabaseHelper {
        return try {
            DatabaseHelper.openDatabase(context)
        } catch (e: SQLException) {
            throw Exception(e.message)
        } ?: throw Exception(context.getString(R.string.dbhelper_found_illegal))
    }
}