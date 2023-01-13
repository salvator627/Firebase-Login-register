package com.example.pornhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.pornhub.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email = binding.editTextTextPersonName.text.toString()
            val password = binding.editTextTextPersonName2.text.toString()

            if (email.isEmpty()){
                binding.editTextTextPersonName.error = "Email cannot be empty"
                binding.editTextTextPersonName.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editTextTextPersonName.error = "Please enter the correct format"
                binding.editTextTextPersonName.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.editTextTextPersonName2.error = "Please fill password"
            }
            LoginFirebase(email,password)
        }

        binding.button4.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"Login success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}