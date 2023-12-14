package kz.app.kolesaupgrade.hello.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.app.kolesaupgrade.Constants.APPLICATION_SHARED_PREFERENCES
import kz.app.kolesaupgrade.Constants.EMAIL_KEY
import kz.app.kolesaupgrade.Constants.USER_KEY
import kz.app.kolesaupgrade.R
import kz.app.kolesaupgrade.databinding.ActivityHelloBinding

class HelloActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelloBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initShared()

        val userName: String? = getSavedUserName()
        val userEmail: String? = getSavedEmail()

        binding.tvHelloActivityName.text = getString(R.string.hello_activity_tv_name, userName)
        binding.tvHelloActivityEmail.text = getString(R.string.hello_activity_tv_email, userEmail)
    }

    private fun initShared() {
        sharedPreferences = getSharedPreferences(APPLICATION_SHARED_PREFERENCES,Context.MODE_PRIVATE)
    }

    private fun getSavedUserName(): String? {
        return sharedPreferences.getString(USER_KEY, null)
    }

    private fun getSavedEmail(): String? {
        return sharedPreferences.getString(EMAIL_KEY, null)
    }
}