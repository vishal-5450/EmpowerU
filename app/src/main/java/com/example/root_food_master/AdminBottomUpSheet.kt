package com.example.root_food_master

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root_food_master.databinding.AdminBottomSheetBinding
import com.example.root_food_master.ngo.NGORegistration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminBottomUpSheet : BottomSheetDialogFragment() {

    private lateinit var binding: AdminBottomSheetBinding
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdminBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance()

        binding.btnVerifyPass.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etAdminPass.text.toString()
            val pattern = "[,/@\\-_.]".toRegex()
            val newEmail = email.replace(pattern, "")
            if (pass.isNotEmpty() && email.isNotEmpty()) {

                var userAuth = false
                var userEmail = "a"
                var userPass = "b"

                database.getReference("USERS").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach { user ->
                            val currectEmail = user.child("email").value.toString()
                            val currectPass = user.child("pass").value.toString()
                            if (currectEmail == newEmail && currectPass == pass) {
                                userEmail = currectEmail
                                userPass = currectPass
                                if (userEmail.isNotEmpty() && userPass.isNotEmpty()) {
                                    userAuth = true
                                    startActivity(Intent(requireActivity(), NgoScreen::class.java))
                                    requireActivity().finish()
                                }
                            } else if (currectEmail == newEmail) {
                                binding.etEmailLayout.error = "Incorrect Password"
                                userAuth = true
                            }

                        }

                        if (!userAuth) {
                            val intent = Intent(requireActivity(), NGORegistration::class.java)
                            intent.putExtra("email", newEmail)
                            intent.putExtra("pass", pass)
                            startActivity(intent)
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })


                database.getReference("USERS")
                    .child("PASSWORD")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val adminPass = snapshot.value
                            if (adminPass == pass) {
                                requireActivity().finish()
                                startActivity(Intent(requireActivity(), NgoScreen::class.java))
                            } else {
                                binding.etAdminPass.error = "Invalid Password !"
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })
            } else {
                binding.etAdminPass.error = "Empty Password"
            }
        }
    }
}