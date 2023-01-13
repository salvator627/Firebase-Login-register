package com.example.pornhub

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pornhub.databinding.FragmentUserBinding
import com.google.firebase.auth.FirebaseAuth

class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding? = null
    lateinit var auth: FirebaseAuth

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null){
            binding.editTextTextPersonName5.setText(user.email)
            binding.editTextTextPersonName6.setText(user.displayName)
        }

        binding.button5.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(context,LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}