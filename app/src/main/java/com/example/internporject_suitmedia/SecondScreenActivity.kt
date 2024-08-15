package com.example.internporject_suitmedia

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.internporject_suitmedia.databinding.ActivitySecondScreenBinding

@Suppress("UNUSED_EXPRESSION")
class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondScreenBinding

    private lateinit var btnChooseUser : Button
    private lateinit var btnBack : ImageView
    private lateinit var tvUsername : TextView
    private lateinit var tvSelectedUsername : TextView

    companion object{
        const val EXTRA_USERNAME = ""
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnChooseUser = binding.btnChooseUser
        btnBack = binding.btnBack
        tvUsername = binding.tvUsername
        tvSelectedUsername = binding.tvSelectedName

        tvUsername.text = getUsername()
        setupSelectedUsername()

        btnBack.setOnClickListener {
            val intent = Intent(this@SecondScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }

        btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUsername() : String{
        val username = intent.getStringExtra(EXTRA_USERNAME)
        if(username != "")
            return username.toString()
        else
            return "Username"
    }

    @SuppressLint("SetTextI18n")
    private fun setupSelectedUsername(){
        val selectedUsername = intent.getStringExtra("Username")

        if(selectedUsername != "")
            tvSelectedUsername.text = "$selectedUsername"
        else
            "Selected User Name"
    }
}