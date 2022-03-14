package com.tom.bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread
import com.google.gson.reflect.TypeToken as TypeToken

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        thread {
            val json = URL("https://atm201605.appspot.com/h").readText()
//            val gson = Gson()
            val transactions = mutableListOf<Transaction>()
            val array = JSONArray(json)
            for (i in 0 until array.length()) {
                val obj: JSONObject= array.getJSONObject(i)
                val amount = obj.getInt("amount")
                val account = obj.getString("account")
                val date = obj.getString("date")
                val type = obj.getInt("type")
                val tran = Transaction(account, date, amount, type)
                transactions.add(tran)
            }

        }
    }
}
class Transaction(val account: String, val date: String, val amount:Int, val type:Int)