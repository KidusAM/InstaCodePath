package com.example.instacodepath

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // first check if user is already logged in
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            loginUser(username, password)
        }

        val btnSignup = findViewById<Button>(R.id.btnSignup)
        btnSignup.setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            signUpUser(username, password)
        }


    }

    private fun signUpUser(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                Toast.makeText(this, "Successfully signed up", Toast.LENGTH_SHORT).show()
                goToMainActivity()
                finish()
            } else {
                Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password) { user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                // Hooray! The user is logged in.
                goToMainActivity()
                finish()
            } else {
                Log.e(TAG, "Login failed")
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
                // Signup failed. Look at the ParseException to see what happened.
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        val TAG = "LoginActivity"
    }
}