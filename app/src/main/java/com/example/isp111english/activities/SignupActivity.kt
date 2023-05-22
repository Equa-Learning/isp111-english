package com.example.isp111english.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.isp111english.R
import com.example.isp111english.application.UserInputCheckStatus
import com.example.isp111english.application.UserService

class SignupActivity : AppCompatActivity() {
    lateinit var mail: EditText
    lateinit var pass: EditText
    lateinit var pass2: EditText
    lateinit var name: EditText
    lateinit var lname: EditText

    private val userService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mail = findViewById(R.id.email)
        pass = findViewById(R.id.password)
        pass2 = findViewById(R.id.password2)
        name = findViewById(R.id.name)
        lname = findViewById(R.id.lastName)

        userService.Init(this)
    }

    fun goToSignIn(view: View) {
//        val intent = Intent(this@SignupActivity, SigninActivity::class.java)
//        startActivity(intent)
        finish()
    }

    fun doRegistration(view: View) {
        val mailString = mail.text.toString().trim().lowercase()
        val passString = pass.text.toString().trim()
        val pass2String = pass2.text.toString().trim()
        val nameString = name.text.toString().trim()
        val lnameString = lname.text.toString().trim()

        if (nameString.isNullOrBlank() || lnameString.isNullOrBlank())
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()

        var userInputCheckStatus = userService.credentialsStatus(mailString, passString, pass2String)
        when (userInputCheckStatus) {
            UserInputCheckStatus.Empty
            -> Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()

            UserInputCheckStatus.WrongEmail
            -> Toast.makeText(this, "Ошибка при заполнении поля Email", Toast.LENGTH_SHORT).show()

            UserInputCheckStatus.PasswordsNotEqual
            -> Toast.makeText(this, "Пароль и повтор не совпадают", Toast.LENGTH_SHORT).show()

            UserInputCheckStatus.WrongEmailOrPassword
            -> throw IllegalArgumentException("При регистрации не может быть неверный пароль")

            UserInputCheckStatus.OK -> {
                userService.saveUser(mailString, passString, nameString, lnameString)

                val intent = Intent(this@SignupActivity, SignupOkActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}