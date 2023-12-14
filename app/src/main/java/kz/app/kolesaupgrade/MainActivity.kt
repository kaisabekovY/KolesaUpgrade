package kz.app.kolesaupgrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.app.kolesaupgrade.Constants.APPLICATION_SHARED_PREFERENCES
import kz.app.kolesaupgrade.Constants.EMAIL_KEY
import kz.app.kolesaupgrade.Constants.USER_KEY
import kz.app.kolesaupgrade.databinding.ActivityMainBinding
import kz.app.kolesaupgrade.hello.presentation.HelloActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkExistingData()
        setupViews()
        initShared()
        setupListeners()
    }

    private fun saveData() {
        saveUserName(binding.tvGreetingName.etContent.text.toString())
        saveUserEmail(binding.tvGreetingEmail.etContent.text.toString())
    }

    private fun saveUserName(userName: String) {
        editor.putString(USER_KEY, userName)
        editor.apply()
    }

    private fun saveUserEmail(email: String) {
        editor.putString(EMAIL_KEY, email)
        editor.apply()
    }

    private fun setupListeners() {
        binding.btnSaveUser.setOnClickListener {
            saveData()
            navigateToHelloActivity()
        }
    }

    private fun initShared() {
        sharedPreferences = getSharedPreferences(
            APPLICATION_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
    }

    private fun navigateToHelloActivity() {
        val toHelloActivityIntent = Intent(this, HelloActivity::class.java)
        startActivity(toHelloActivityIntent)
    }

    private fun isUserNameSaved(): Boolean {
        val sharedPreferences = getSharedPreferences(
            APPLICATION_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )

        return sharedPreferences.contains(USER_KEY)
    }

    private fun setupViews() {
        binding.tvGreetingName.tvContent.text = resources.getString(R.string.main_activity_tv_name)
        binding.tvGreetingName.etContent.hint = resources.getString(R.string.main_activity_hint_name)
        binding.tvGreetingEmail.tvContent.text = resources.getString(R.string.main_activity_tv_email)
        binding.tvGreetingEmail.etContent.hint = resources.getString(R.string.main_activity_hint_email)
    }

    private fun checkExistingData() {
        if(isUserNameSaved()) {
            navigateToHelloActivity()
            finish()

            return
        }
    }
}