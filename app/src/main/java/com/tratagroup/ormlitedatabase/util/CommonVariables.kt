package com.tratagroup.ormlitedatabase.util

import android.Manifest

/**
 * Created by Ganesh Junghare on 9/26/2019.
 */
object CommonVariables {

    val PERMISSIONS = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    const val REQUEST_PERMISSION = 909

    object Constants{
        const val DB_NAME = "ganeshJ"

    }
}