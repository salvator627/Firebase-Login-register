package com.example.pornhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.pornhub.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.button2.setOnClickListener {
            val email = binding.editTextTextPersonName3.text.toString()
            val password = binding.editTextTextPersonName4.text.toString()

            if(email.isEmpty()){
                binding.editTextTextPersonName3.error = "Email cannot be empty"
                binding.editTextTextPersonName3.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editTextTextPersonName3.error = "Please enter the correct format"
                binding.editTextTextPersonName3.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.editTextTextPersonName4.error = "Password cannot be empty"
                binding.editTextTextPersonName4.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6){
                binding.editTextTextPersonName4.error = "Password minimum 6 characters"
                binding.editTextTextPersonName4.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email,password)
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"Register success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}