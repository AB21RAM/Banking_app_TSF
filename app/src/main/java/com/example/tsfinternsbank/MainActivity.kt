package com.example.tsfinternsbank

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tsfinternsbank.DataBase.DbHelper
import com.example.tsfinternsbank.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.AllCus.setOnClickListener {
            all_customes()
        }
        binding.transactions.setOnClickListener {
            transaction()
        }

    }
    fun all_customes(){
        val intent1 = Intent(this,All_Customers::class.java)
        startActivity(intent1)
    }
    fun transaction(){
        val intent2 = Intent(this,ListViewOfData::class.java)
        startActivity(intent2)
    }
}