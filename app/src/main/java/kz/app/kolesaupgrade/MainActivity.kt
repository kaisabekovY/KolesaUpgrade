package kz.app.kolesaupgrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kz.app.kolesaupgrade.Constants.APPLICATION_SHARED_KEY
import kz.app.kolesaupgrade.Constants.APPLICATION_SHARED_PREFERENCES
import kz.app.kolesaupgrade.hello.presentation.HelloActivity

class MainActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var etName: EditText

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initShared()
        initViews()
        setupListeners()
    }

    private fun saveUserName(userName: String) {
        editor.putString(APPLICATION_SHARED_KEY, userName)
        editor.apply()
    }

    private fun initViews() {
        saveButton = findViewById(R.id.btn_save_user)
        etName = findViewById(R.id.et_user_name)
    }

    private fun setupListeners() {
        saveButton.setOnClickListener {
            navigateToHelloActivity()
            saveUserName(etName.text.toString())
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
}