package com.example.tsfinternsbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycleviewsqlitedata.ReCVAdapter.RcAdapter
import com.example.tsfinternsbank.DataBase.DbHelper
import com.example.tsfinternsbank.Model.Customers
import com.example.tsfinternsbank.databinding.ActivityAllCustomersBinding
import com.example.tsfinternsbank.databinding.ActivityMainBinding

class All_Customers : AppCompatActivity() {
    lateinit var binding: ActivityAllCustomersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myDataset = getItemsList()
        val recyclerView = binding.recyclerView
        var adapterRc = RcAdapter(this,myDataset)
        recyclerView.adapter = adapterRc


        // code for click listener
        adapterRc.setOnItemClickListener(object : RcAdapter.onItemClicklistener{
            override fun onItemlick(position: Int) {
                val data = myDataset[position]
                val intent = Intent(this@All_Customers , Details::class.java)
                intent.putExtra("name",data.name)
                intent.putExtra("balance",data.balance.toString())
                intent.putExtra("id",data.id.toString())
                intent.putExtra("Acc_type",data.acc_type)
                intent.putExtra("Address",data.address)
                startActivity(intent)// start the activity
            }
        })
    }

    private fun getItemsList(): ArrayList<Customers> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DbHelper = DbHelper(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val datalist: ArrayList<Customers> = databaseHandler.allDataList()
        return datalist
    }
}