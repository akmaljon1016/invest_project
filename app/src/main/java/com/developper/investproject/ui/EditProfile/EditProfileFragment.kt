package com.developper.investproject.ui.EditProfile

import android.app.ProgressDialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.developper.investproject.HomeActivity
import com.developper.investproject.R
import com.developper.investproject.SignInActivity
import com.developper.investproject.`class`.Constants
import com.developper.investproject.databinding.FragmentEditProfileBinding
import com.google.firebase.auth.FirebaseAuth


class EditProfileFragment : Fragment() {
    lateinit var preferences: SharedPreferences
    lateinit var binding: FragmentEditProfileBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var progressDialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    //onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog= ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Loading...")
        progressDialog.setCanceledOnTouchOutside(false)

//bazadan olish

        preferences = requireActivity().getSharedPreferences(Constants.baza, MODE_PRIVATE)

        val user: String = preferences.getString(Constants.key_User, null).toString()
        val email: String = preferences.getString(Constants.key_Email, null).toString()
        val phone: String = preferences.getString(Constants.key_Number, null).toString()

        binding.edEmail.setText(email)
        binding.edSurname.setText(user)
        binding.edPhone.setText(phone)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
//bazadan olish


//bazaga joylas
        }
        binding.btnSave.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val user = binding.edSurname.text.toString()
            val phone = binding.edPhone.text.toString()
            if (email.isNullOrEmpty()) {
               progressDialog.dismiss()
                binding.edEmail.setHintTextColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.red
                    )
                )
            } else if (user.isNullOrEmpty()) {
                progressDialog.dismiss()
                binding.edPhone.setHintTextColor(
                    ContextCompat.getColor(
                        requireActivity(), R.color.red
                    )
                )
            } else {
                progressDialog.dismiss()
                saveUser(phone=phone, email=email, user=user)
                Toast.makeText(requireActivity(), "Saved number $phone", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
    //Firebase

    }
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            val email = firebaseUser.email
            binding.edEmail.text = email
        } else {
            Toast.makeText(requireContext(), "Load email and user", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveUser(phone: String, email: String, user: String) {
        val myEdit: SharedPreferences.Editor = preferences.edit()
        myEdit.putString(Constants.key_Number, phone)
        myEdit.putString(Constants.key_Email, email)
        myEdit.putString(Constants.key_User, user)

        myEdit.apply()
        //requireActivity().finish()
    }
}
//bazaga joylash
