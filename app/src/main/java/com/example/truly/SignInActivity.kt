package com.example.truly

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.truly.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Objects

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            login()
        }

        binding.signup2.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        val email = Objects.requireNonNull(binding.emailsignin.text).toString()
        val password = Objects.requireNonNull(binding.passwordsignin.text).toString()
        if (TextUtils.isEmpty(email)) {
            binding.emailsignin.error = "Enter your Mail"
            return
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordsignin.error = "Enter your Password"
            return
        }
        progressDialog.setMessage("Please wait.....")
        progressDialog.show()
        progressDialog.setCanceledOnTouchOutside(false)
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@SignInActivity, "Successfully Signed In", Toast.LENGTH_LONG)
                        .show()
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@SignInActivity, "Login Failed!", Toast.LENGTH_LONG).show()
                }
                progressDialog.dismiss()
            }
    }
}