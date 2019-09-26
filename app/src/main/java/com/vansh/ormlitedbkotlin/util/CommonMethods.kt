package com.vansh.ormlitedbkotlin.util

import java.io.File

/**
 * Created by Ganesh Junghare on 9/26/2019.
 */
object CommonMethods {

    fun checkDataBase(dbFilePath: String) = File(dbFilePath).exists()
}