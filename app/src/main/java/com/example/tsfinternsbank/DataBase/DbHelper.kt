package com.example.tsfinternsbank.DataBase

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.COLUMN_ACC_TYPE
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.COLUMN_ADDRESS
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.COLUMN_INI_BALANCE
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.COLUMN_NAME
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.KEY_ROW_ID
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.KEY_TRANS_ID
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.COLUMN_SENDER
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.COLUMN_RECEIVER
import com.example.tsfinternsbank.DataBase.DbHelper.FeedEntry.COLUMN_AMOUNT
import com.example.tsfinternsbank.Model.Customers
import com.example.tsfinternsbank.Model.transactions


class DbHelper(context: Context) : SQLiteOpenHelper(context,"CUSTOMER",null,2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CUS_INFO ($KEY_ROW_ID integer primary key autoincrement , $COLUMN_NAME TEXT , $COLUMN_INI_BALANCE INT , $COLUMN_ACC_TYPE TEXT ,$COLUMN_ADDRESS TEXT)")
        db?.execSQL("CREATE TABLE TRANSACT($KEY_TRANS_ID integer primary key autoincrement , $COLUMN_SENDER TEXT , $COLUMN_RECEIVER TEXT , $COLUMN_AMOUNT INTEGER) ")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Yash Chabukswar',10000,'CURRENT','Thane')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Prasad Choudhari',11000,'CURRENT','Central Mumbai')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Soham Borse ',13000,'REGULAR','South Mumbai')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Onkar Yewale',14000,'CURRENT','Satara')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Vishal Ugalmugale',15000,'CURRENT','Mumbai Suburban')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Nihaar Mascarhnes',16000,'CURRENT','Mumbai')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Omkar Deshmukh',12000,'CURRENT','Ratnagiri')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Avishkar Bhosale',17000,'CURRENT','Rahimatpur')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Aditya Thorat',1800,'CURRENT','Mumbai')")
        db?.execSQL("INSERT INTO CUS_INFO (NAME ,BALANCE , TYPE ,ADDRESS)VALUES('Sahil Kuware',15000,'CURRENT','Thane')")
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS TRANSACT ")
        db?.execSQL("DROP TABLE IF EXISTS CUS_INFO ")
        onCreate(db)
    }
    object FeedEntry : BaseColumns {
        const val KEY_ROW_ID = "_id"
        const val COLUMN_NAME= "NAME"
        const val COLUMN_INI_BALANCE = "BALANCE"
        const val COLUMN_ACC_TYPE = "TYPE"
        const val COLUMN_ADDRESS = "ADDRESS"

        const val KEY_TRANS_ID = "ID"
        const val COLUMN_SENDER = "SENDER"
        const val COLUMN_RECEIVER = "RECEIVER"
        const val COLUMN_AMOUNT = "AMOUNT"
    }
    fun allDataList(): ArrayList<Customers> {
        val datalist : ArrayList<Customers> = ArrayList<Customers>()
        val selectQuery = "SELECT  * FROM CUS_INFO"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var balance : Int
        var acc_type : String
        var address :String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ROW_ID))
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                balance = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INI_BALANCE))
                acc_type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACC_TYPE))
                address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))

                val emp = Customers(id = id, name = name, balance = balance,acc_type = acc_type , address = address)
                datalist.add(emp)

            } while (cursor.moveToNext())
        }
        return datalist
    }
    fun nameDataList(): ArrayList<String> {
        val datalist: ArrayList<String> = ArrayList<String>()
        val selectQuery = "SELECT  * FROM CUS_INFO"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var balance : Int
        var acc_type : String
        var address :String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ROW_ID))
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                balance = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INI_BALANCE))
                acc_type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACC_TYPE))
                address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))

                val emp = Customers(id = id, name = name, balance = balance,acc_type = acc_type , address = address).name.toString()
                datalist.add(emp)

            } while (cursor.moveToNext())
        }
        return datalist
    }
    fun getTransData() :ArrayList<transactions>{
        var arrayList :ArrayList<transactions> = ArrayList<transactions>()
        val selectQuery = "SELECT  * FROM TRANSACT"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id :Int
        var sender: String
        var receiver: String
        var amount: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TRANS_ID))
                sender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENDER))
                receiver = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RECEIVER))
                amount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))

                val trans = transactions(id = id, sender = sender, receiver = receiver, amount = amount)
                arrayList.add(trans)

            } while (cursor.moveToNext())
        }
        return arrayList
    }
}