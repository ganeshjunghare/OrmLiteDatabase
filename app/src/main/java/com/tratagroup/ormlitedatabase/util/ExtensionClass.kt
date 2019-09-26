package com.tratagroup.ormlitedatabase.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * Created by Ganesh Junghare on 9/26/2019.
 */


fun String.toast(context: Context, len: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, this, len).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}
