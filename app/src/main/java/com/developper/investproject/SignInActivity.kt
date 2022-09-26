package com.developper.investproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.developper.investproject.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
//    lateinit var actionBar: ActionBar
    lateinit var progressDialog:ProgressDialog
    lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        actionBar=supportActionBar!!
//        actionBar.setTitle("Sign Up")
//        actionBar.setDisplayHomeAsUpEnabled(true)
//        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.btnSignUp.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email=binding.emailEt.text.toString()
        password=binding.passET.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.error="Invalid format email"
        }
        else if(TextUtils.isEmpty(password)){
            binding.passET.error="Please enter password"
        }
        else if(password.length<6){
            binding.passET.error="Password must atleast 6 characters long"
        }
        else{
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser=firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, "SignUp Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}