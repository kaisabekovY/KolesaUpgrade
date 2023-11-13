package kz.app.kolesaupgrade.hello.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kz.app.kolesaupgrade.Constants.APPLICATION_SHARED_KEY
import kz.app.kolesaupgrade.Constants.APPLICATION_SHARED_PREFERENCES
import kz.app.kolesaupgrade.R

class HelloActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        initShared()
        initViews()

        val userName: String = getSavedUserName()

        tvName.text = userName
    }

    private fun initViews() {
        tvName = findViewById(R.id.tv_hello_activity)
    }

    private fun initShared() {
        sharedPreferences = getSharedPreferences(APPLICATION_SHARED_PREFERENCES,Context.MODE_PRIVATE)
    }

    private fun getSavedUserName(): String{
        return sharedPreferences.getString(APPLICATION_SHARED_KEY,"World!") ?: "World!"
    }
}