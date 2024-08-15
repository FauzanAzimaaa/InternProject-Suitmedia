package com.example.internporject_suitmedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internporject_suitmedia.databinding.ActivityThirdScreenBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivityThirdScreenBinding
    private lateinit var btnBack : ImageView

    companion object{
        private val TAG = ThirdScreenActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnBack = binding.btnBack

        btnBack.setOnClickListener {
            val intent = Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java)
            startActivity(intent)
        }

        setupRecyclerView()
        getList()
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        binding.rvList.setLayoutManager(layoutManager)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)
    }

    private fun getList(){
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://reqres.in/api/users?page=1&per_page=10"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                binding.progressBar.visibility = View.INVISIBLE
                val listItem = ArrayList<Item>()
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val arrayData = jsonObject.getJSONArray("data")
                    for(i in 0 until arrayData.length()){
                        val jsonObj = arrayData.getJSONObject(i)
                        val firstName = jsonObj.getString("first_name")
                        val lastname = jsonObj.getString("last_name")
                        val email = jsonObj.getString("email")
                        val avatar = jsonObj.getString("avatar")
                        listItem.add(Item(firstName, lastname, email, avatar))
                    }
                    val adapter = ListAdapter(listItem)
                    binding.rvList.adapter = adapter
                }catch (e : Exception){
                    Toast.makeText(this@ThirdScreenActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray,
                error: Throwable?
            ) {
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(this@ThirdScreenActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}