package com.example.isp111english.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.isp111english.R
import com.example.isp111english.application.UserInputCheckStatus
import com.example.isp111english.application.UserService

class SigninActivity : AppCompatActivity() {

    lateinit var mail: EditText
    lateinit var pass: EditText
    lateinit var remember: CheckBox
    val userService: UserService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        mail = findViewById(R.id.signinEmailField)
        pass = findViewById(R.id.signinPasswordField)
        remember = findViewById(R.id.rememberMe)

        userService.Init(getSharedPreferences("TABLEE", MODE_PRIVATE))
        remember.isChecked = userService.rememberCredentials
        if (remember.isChecked) {
            mail.setText(userService.signinEmail)
            pass.setText(userService.signinPass)
        }
    }

    fun doLogin(view: View) {
        userService.rememberCredentials = remember.isChecked
        if (!remember.isChecked)
            userService.removeSignInPrefs()

        val mailString = mail.text.toString()
        val passString = pass.text.toString()

        var userInputCheckStatus = userService.credentialsStatus(mailString, passString)
        when (userInputCheckStatus) {
            UserInputCheckStatus.Empty
            -> AlertDialog.Builder(this)
                .setTitle("Заполните текстовые поля")
                .setPositiveButton("OK", null)
                .create().show()

            UserInputCheckStatus.WrongEmail
            -> Toast.makeText(this, "ошибка при заполнении поля Email", Toast.LENGTH_SHORT).show()

            UserInputCheckStatus.WrongEmailOrPassword
            -> AlertDialog.Builder(this)
                .setTitle("Логин и пароль не соответствуют зарегистрированным")
                .setPositiveButton("OK", null)
                .create().show()

            UserInputCheckStatus.PasswordsNotEqual
            -> throw IllegalArgumentException("При входе не может быть разных паролей")

            UserInputCheckStatus.OK -> {
                if (remember.isChecked)
                    userService.saveData(mailString, passString)

                goToKlaus()
            }
        }
    }

    fun goToKlaus() {
        val intent = Intent(this@SigninActivity, ChooseVersionActivity::class.java)
        startActivity(intent)
        //finish()
    }

    fun goToRegistration(view: View) {
        val intent = Intent(this@SigninActivity, SignupActivity::class.java)
        startActivity(intent)
        //finish()
    }
    fun onBackPressed(view: View) {
        finish()
    }
}