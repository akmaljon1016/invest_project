package com.developper.investproject

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.developper.investproject.`class`.Constants.Companion.baza
import com.developper.investproject.`class`.Constants.Companion.key_Email
import com.developper.investproject.`class`.Constants.Companion.key_User
import com.developper.investproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
//    lateinit var actionBar: ActionBar
    lateinit var progressDialog:ProgressDialog
    lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        //actionbar options
//        actionBar=supportActionBar!!
//        actionBar.title="Login"

        //progress dialog options
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)
        //firebase options
        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()
        binding.notRegistred.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }

        binding.btnSignIn.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email=binding.emailEt.text.toString().trim()
        password=binding.passET.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.setError("Invalid email format")
        }else if(TextUtils.isEmpty(password)){
            binding.passET.error="Please enter password"
        }
        else{
            firebaseLogin()
        }

    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser=firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this, "Logged in as $email", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
            .addOnFailureListener{
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed try again", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser(){
        //if user already logged in profile activity
        //get current user
        if (firebaseAuth.currentUser!=null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }
}