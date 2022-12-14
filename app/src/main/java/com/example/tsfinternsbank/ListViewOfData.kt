package com.example.tsfinternsbank

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import com.example.tsfinternsbank.Adapter.MyListAdapter
import com.example.tsfinternsbank.DataBase.DbHelper
import com.example.tsfinternsbank.Model.transactions
import com.example.tsfinternsbank.databinding.ActivityListViewOfDataBinding


class ListViewOfData : AppCompatActivity() {

    lateinit var binding: ActivityListViewOfDataBinding
    lateinit var db : SQLiteDatabase
    lateinit var curser : Cursor
    lateinit var adapter: SimpleCursorAdapter
    lateinit var helper: DbHelper
    lateinit var arrayList : ArrayList<transactions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewOfDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        helper = DbHelper(applicationContext)
        db = helper.readableDatabase
        curser = db.rawQuery("SELECT * FROM TRANSACT",null)
        var arrayList :ArrayList<transactions> = ArrayList<transactions>()
        loaddatainlistView()
    }

    private fun loaddatainlistView() {
        arrayList = helper.getTransData()
        val list_adapter : MyListAdapter = MyListAdapter(this,arrayList)
        binding.List.adapter = list_adapter
        list_adapter.notifyDataSetChanged()
    }
}