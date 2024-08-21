package com.example.login_api

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var imm: InputMethodManager
    private lateinit var mainActivity: LinearLayout
    private lateinit var edt_txt_email: EditText
    private lateinit var edt_txt_password: EditText
    private lateinit var login_btn: Button
    private lateinit var userDatabase: UserDatabase
    private lateinit var retrofit: Retrofit

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        mainActivity = findViewById(R.id.main)
        edt_txt_email = findViewById(R.id.edt_txt_email)
        edt_txt_password = findViewById(R.id.edt_txt_password)
        login_btn = findViewById(R.id.login_btn)


        mainActivity.setOnClickListener {
            imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken , 0)
        }

        login_btn.setOnClickListener {
            if (edt_txt_email.text.toString().isEmpty() || edt_txt_password.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill both the fields", Toast.LENGTH_SHORT).show()
            }
            else {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val api = retrofit.create(RetrofitApi::class.java)

                api.getUser().enqueue(object : Callback<User> {
                    override fun onResponse(p0: Call<User>, p1: Response<User>) {
                        Log.d("response", p1.raw().toString())
                    }
                    override fun onFailure(p0: Call<User>, p1: Throwable) {
                        Log.d("failure",p1.message.toString())
                    }
                })
            }
        }
    }
}


//  userDatabase = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "userData").build()


