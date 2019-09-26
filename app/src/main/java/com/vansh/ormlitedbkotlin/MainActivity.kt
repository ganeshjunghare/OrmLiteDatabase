package com.vansh.ormlitedbkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vansh.ormlitedbkotlin.bean.UserBean
import com.vansh.ormlitedbkotlin.database.daohelper.UserDao
import com.vansh.ormlitedbkotlin.util.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idBtnSubmit.setOnClickListener {

            if (idEtUserName.text.toString().isEmpty()) {
                getString(R.string.enter_username).toast(this)
                return@setOnClickListener
            }
            if (idEtUserMobile.text.toString().isEmpty()) {
                getString(R.string.enter_usermobile).toast(this)
                return@setOnClickListener
            }
            val userBean = UserBean()
            userBean.userName = idEtUserName.text.toString()
            userBean.userMobile = idEtUserMobile.text.toString()
            val userDao = UserDao(this)
            userDao.createRecord(userBean)
        }
    }
}
