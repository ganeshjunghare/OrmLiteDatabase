package com.vansh.ormlitedbkotlin

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import com.vansh.ormlitedbkotlin.util.CommonVariables

class SplashScreen : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (!hasPermissions(this, *CommonVariables.PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, CommonVariables.PERMISSIONS, CommonVariables.REQUEST_PERMISSION)
        } else {
            init()
        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            CommonVariables.REQUEST_PERMISSION -> {
                var flag = false
                var isNeverAskCalled = true

                var i = 0
                val len = grantResults.size
                while (i < len) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            isNeverAskCalled = shouldShowRequestPermissionRationale(permissions[i])
                        }
                        flag = true
                        break
                    }
                    i++
                }
                if (isNeverAskCalled) {
                    if (flag) {
                        ActivityCompat.requestPermissions(
                            this,
                            CommonVariables.PERMISSIONS,
                            CommonVariables.REQUEST_PERMISSION
                        )
                    } else {
                        init()
                    }
                } else {
                    showAlertDailogForEnablePermission()
                }
            }
        }
    }

    private fun showAlertDailogForEnablePermission() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.enable_permission))
        dialog.setMessage(getString(R.string.enable_permission_message))
        dialog.setCancelable(false)

        dialog.setPositiveButton(getString(android.R.string.ok)) { dialog, which ->
            dialog.dismiss()
            try {
                Thread.sleep(0, 10)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val intent = Intent()
            intent.action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:$packageName")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            startActivity(intent)
            finish()
        }

        dialog.setNegativeButton(getString(android.R.string.cancel)) { dialog, which ->
            dialog.dismiss()
            finish()
        }

        val alertDialog = dialog.create()
        alertDialog.show()
    }

    private fun init() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}
