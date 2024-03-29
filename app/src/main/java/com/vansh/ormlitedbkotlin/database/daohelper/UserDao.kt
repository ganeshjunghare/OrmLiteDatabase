package com.vansh.ormlitedbkotlin.database.daohelper

import android.content.Context
import com.vansh.ormlitedbkotlin.R
import com.vansh.ormlitedbkotlin.bean.UserBean
import com.vansh.ormlitedbkotlin.database.DatabaseHelper
import java.sql.SQLException

/**
 * Created by Ganesh Junghare on 9/26/2019.
 */
class UserDao(private val context: Context) {

    @Throws(Exception::class)
    fun createRecord(userBean: UserBean) {
        try {
            val helperUtil = helperUtil()
            val userDao = helperUtil.getUserDao()
            userDao?.create(userBean)
            helperUtil.closeDatabase()
        } catch (e: SQLException) {
            throw Exception(e.message)
        }
    }

    private fun helperUtil(): DatabaseHelper {
        return try {
            DatabaseHelper.openDatabase(context)
        } catch (e: SQLException) {
            throw Exception(e.message)
        } ?: throw Exception(context.getString(R.string.dbhelper_found_illegal))
    }
}