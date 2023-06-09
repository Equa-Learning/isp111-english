package com.example.isp111english.application

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class UserService {

    val pattern = ("[a-z]{1,100}" + "@" + "[a-z]{1,6}" + "\\." + "[a-z]{1,5}")

    fun isEmailValid(text: String): Boolean {
        return Pattern.compile(pattern).matcher(text).matches()
    }

    fun Init(context: Activity) {
        _pref = context.getSharedPreferences("TABLEE", AppCompatActivity.MODE_PRIVATE)
    }

    private var _pref: SharedPreferences? = null

    private val pref: SharedPreferences
        get() {
            if (_pref == null) {
                throw Exception("shared prefs not initialized")
            }
            return _pref as SharedPreferences;
        }

    var rememberCredentials: Boolean
        get() {
            return pref.getBoolean(TAG_DOREMEMBER, false)
        }
        set(value) {
            var editor = pref.edit()
            editor.putBoolean(TAG_DOREMEMBER, value)
            editor.apply()
        }

    var goodPoints: Int
        get() {
            return pref.getInt(TAG_GOODPOINTS, -1)
        }
        set(value) {
            var editor = pref.edit()
            editor.putInt(TAG_GOODPOINTS, value)
            editor.apply()
        }

    val signinEmail: String
        get() {
            return pref.getString(TAG_SIGNEMAIL, "")!!
        }
    val signinPass: String
        get() {
            return pref.getString(TAG_SIGNPASS, "")!!
        }

    private var currentUser: String
        get() {
            return pref.getString(TAG_CURRENTUSER, "")!!
        }
        set(value) {
            var editor = pref.edit()
            editor.putString(TAG_CURRENTUSER, value)
            editor.apply()
        }

    val userFullName: String
        get() {
            if (userLastName == "")
                return userName
            if (userName == "")
                return userLastName
            return "$userName $userLastName"
        }

    val userName
        get() = pref.getString("[$currentUser]$TAG_NAME", "")!!

    val userLastName
        get() = pref.getString("[$currentUser]$TAG_LASTNAME", "")!!

    private val TAG_DOREMEMBER = "signinDoRemember"
    private val TAG_GOODPOINTS = "goodPoints"

    private val TAG_SIGNEMAIL = "signinEmail"
    private val TAG_SIGNPASS = "signinPass"

    private val TAG_CURRENTUSER = "userEmail"
    private val TAG_PASS = "userPass"
    private val TAG_NAME = "userName"
    private val TAG_LASTNAME = "userLastName"

    fun saveUser(mail: String, pass: String, name: String, lastname: String) {
        var editor = pref.edit()
        editor.putString("[$mail]$TAG_PASS", pass)
        editor.putString("[$mail]$TAG_NAME", name)
        editor.putString("[$mail]$TAG_LASTNAME", lastname)
        editor.apply()
    }

    fun saveLoginScreenData(mail: String, pass: String) {
        var editor = pref.edit()
        editor.putString(TAG_SIGNEMAIL, mail)
        editor.putString(TAG_SIGNPASS, pass)
        editor.apply()
    }

    fun cleanLoginScreenData() {
        var editor = pref.edit()
        editor.remove(TAG_SIGNEMAIL)
        editor.remove(TAG_SIGNPASS)
        editor.apply()
    }

    fun checkAuth(mail: String, pass: String): Boolean {
        if (pref.getString("[$mail]$TAG_PASS", null) == pass) {
            currentUser = mail
            return true
        }
        var editor = pref.edit()
        editor.remove(TAG_CURRENTUSER)
        editor.apply()
        return false
    }

    fun credentialsStatus(mail: String, pass: String, pass2: String? = null): UserInputCheckStatus {
        if (mail.isNullOrEmpty() || pass.isNullOrEmpty())
            return UserInputCheckStatus.Empty

        if (!isEmailValid(mail))
            return UserInputCheckStatus.WrongEmail

        if (pass2 == null && !checkAuth(mail, pass))
            return UserInputCheckStatus.WrongEmailOrPassword

        if (pass2 != null && pass != pass2)
            return UserInputCheckStatus.PasswordsNotEqual

        return UserInputCheckStatus.OK
    }

}