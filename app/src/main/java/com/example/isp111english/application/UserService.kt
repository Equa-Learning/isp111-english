package com.example.isp111english.application

import android.content.SharedPreferences
import java.lang.Exception
import java.util.regex.Pattern

class UserService {

    val pattern = ("[a-z]{1,100}"+"@"+"[a-z]{1,6}"+"\\."+"[a-z]{1,5}")

    fun isEmailValid(text: String):Boolean {
        return Pattern.compile(pattern).matcher(text).matches()
    }

    fun Init( value:SharedPreferences) {
        _pref = value
    }

    var _pref: SharedPreferences? = null

    val pref: SharedPreferences
        get() {
            if (_pref == null ) {
                throw Exception("shared prefs not initialized")
            }
            return _pref as SharedPreferences;
        }

    var rememberCredentials: Boolean
        get () {
            return pref.getBoolean("signinDoRemember", false)
        }
        set(value) {
            var editor = pref.edit()
            editor.putBoolean("signinDoRemember", value)
            editor.apply()
        }

    val signinEmail: String?
        get () {
            return pref.getString(TAG_SIGNEMAIL, "")
        }
    val signinPass: String?
        get () {
            return pref.getString(TAG_SIGNPASS, "")
        }

    private val TAG_SIGNEMAIL = "signinEmail"
    private val TAG_SIGNPASS = "signinPass"

    private val TAG_EMAIL = "userEmail"
    private val TAG_PASS = "userPass"
    private val TAG_NAME = "userName"
    private val TAG_LASTNAME = "userLastName"

    fun saveUser(mail: String, pass: String, name: String, lastname: String) {
        var editor = pref.edit()
        editor.putString(TAG_EMAIL, mail)
        editor.putString(TAG_PASS, pass)
        editor.putString(TAG_NAME, name)
        editor.putString(TAG_LASTNAME, lastname)
        editor.apply()
    }

    fun saveData(mail: String, pass: String) {
        var editor = pref.edit()
        editor.putString(TAG_SIGNEMAIL, mail)
        editor.putString(TAG_SIGNPASS, pass)
        editor.apply()
    }

    fun removeSignInPrefs() {
        var editor = pref.edit()
        editor.remove(TAG_SIGNEMAIL)
        editor.remove(TAG_SIGNPASS)
        editor.apply()
    }

    fun checkAuth(mail :String, pass:String): Boolean {
        if (
            pref.getString(TAG_EMAIL,null) == mail
            && pref.getString(TAG_PASS,null) == pass
        ) {
            return true;
        }
        return false;
    }

    fun credentialsStatus(mail:String, pass:String, pass2 : String? = null): UserInputCheckStatus {
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