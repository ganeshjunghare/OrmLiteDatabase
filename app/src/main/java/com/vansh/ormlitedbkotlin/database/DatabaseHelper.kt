package com.vansh.ormlitedbkotlin.database

import android.content.Context
import android.os.Environment
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.vansh.ormlitedbkotlin.R
import com.vansh.ormlitedbkotlin.bean.UserBean
import com.vansh.ormlitedbkotlin.util.CommonMethods
import com.vansh.ormlitedbkotlin.util.CommonVariables
import net.sqlcipher.database.SQLiteDatabase
import java.io.File
import java.lang.Exception
import java.sql.SQLException

/**
 * Created by Ganesh Junghare on 9/26/2019.
 */
class DatabaseHelper(context: Context, databaseName: String) : OrmLiteSqliteOpenHelper(
    context,
    Environment.getExternalStorageDirectory().toString() + File.separator + context.getString(R.string.app_name) + File.separator + databaseName + ".db",
    null,
    DATABASEVERSION,
    DATABASE_PASSWORD
) {
    companion object {
        var count = 0
        private var databaseHelper: DatabaseHelper? = null
        const val DATABASE_PASSWORD = "ganesh@123"
        const val DATABASEVERSION = 1
        const val USER_TABLE = "User"

        @Throws(Exception::class)
        fun openDatabase(context: Context): DatabaseHelper? {
            if (databaseHelper == null) {
                SQLiteDatabase.loadLibs(context)
                databaseHelper = DatabaseHelper(context, CommonVariables.Constants.DB_NAME)
            }
            val dbFilePath = Environment.getExternalStorageDirectory().toString() +
                    File.separator +
                    context.getString(R.string.app_name) +
                    File.separator +
                    CommonVariables.Constants.DB_NAME + ".db"

            if (!CommonMethods.checkDataBase(dbFilePath)) {
                throw Exception(context.getString(R.string.db_not_exists))
            }
            return databaseHelper
        }
    }

    init {
        getWritableDatabase(DATABASE_PASSWORD)
    }

    private var userDao: Dao<UserBean, Int>? = null

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists<UserBean>(connectionSource, UserBean::class.java)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {
    }

    fun getUserDao(): Dao<UserBean, Int>? {
        return try {
            ++count
            if (userDao == null) {
                userDao = this.getDao<Dao<UserBean, Int>, UserBean>(UserBean::class.java)
            }
            userDao
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    @Synchronized
    fun closeDatabase() {
        --count
        if (count == 0) {
            databaseHelper?.close()
            databaseHelper = null
            userDao = null
        }
    }
}