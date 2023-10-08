package com.example.truly

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.truly.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Objects

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            register()
        }
        binding.signin2.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun register() {
        val name = Objects.requireNonNull(binding.namesignin.text).toString()
        val email = Objects.requireNonNull(binding.emailsignin.text).toString()
        val password = Objects.requireNonNull(binding.passwordsignin.text).toString()
        val class_ = Objects.requireNonNull(binding.classsignin.text).toString()
        if (TextUtils.isEmpty(name)) {
            binding.namesignin.error = "Enter your Name"
            return
        } else if (TextUtils.isEmpty(email)) {
            binding.emailsignin.error = "Enter your Email"
            return
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordsignin.error = "Enter your Password"
            return
        } else if (TextUtils.isEmpty(class_)) {
            binding.classsignin.error = "Enter your Class"
            return
        } else if (password.length < 5) {
            binding.passwordsignin.error = "Password is too short"
            return
        } else if (!isValidEmail(email)) {
            binding.emailsignin.error = "Invalid Email"
            return
        }

        progressDialog.setMessage("Please wait...")
        progressDialog.show()
        progressDialog.setCanceledOnTouchOutside(false)
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Successfully Registered",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("Error Sign Up",task.result.toString())
                    Toast.makeText(
                        this@SignUpActivity,
                        "Registration Failed!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                progressDialog.dismiss()
            }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}