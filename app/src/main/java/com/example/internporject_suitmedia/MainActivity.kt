package com.example.internporject_suitmedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.internporject_suitmedia.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
//    Lateinit TextInput Declaration
    private lateinit var etPalindrome : TextInputEditText
    private lateinit var etUsername : TextInputEditText

//    Lateinit Button Declaration
    private lateinit var btnCheck : Button
    private lateinit var btnNext : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etPalindrome = binding.etPalindrome
        etUsername = binding.etUsername

        btnCheck = binding.btnCheck
        btnNext = binding.btnNext

        btnCheck.setOnClickListener{
            val valPalindrome = etPalindrome.text.toString()
            if(valPalindrome == "")
                Toast.makeText(this, "Input should be filled!", Toast.LENGTH_SHORT).show()
            else{
                val outputPalindrome = checkPalindrome(valPalindrome)
                Toast.makeText(this, outputPalindrome, Toast.LENGTH_SHORT).show()
            }
        }

        btnNext.setOnClickListener {
            val valUsername = etUsername.text.toString()
            val intentUsernameData = Intent(this@MainActivity, SecondScreenActivity::class.java)
            intentUsernameData.putExtra(SecondScreenActivity.EXTRA_USERNAME,valUsername)
            startActivity(intentUsernameData)
        }
    }

    fun checkPalindrome(word : String) : String{
        val removeSpace = word.replace(" ", "")
        val lowerCaseWord = removeSpace.toLowerCase(Locale.ROOT)
        val reversedWord = lowerCaseWord.reversed()

        Log.d("Palindrome","lowercase word = $lowerCaseWord")
        Log.d("Palindrome","reversed word = $reversedWord")

        if(lowerCaseWord == reversedWord) return "isPalindrome"
        else return "not palindrome"
    }
}