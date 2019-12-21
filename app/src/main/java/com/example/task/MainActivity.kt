package com.example.task

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.lang.StringBuilder


class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnList1.setOnClickListener(this)
        btnList2.setOnClickListener(this)
        btnList3.setOnClickListener(this)
        btnList1.performClick()
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnList1 -> {
                atvCity.text = parseList1(readJSONFromAsset("test1.json")!!)
            }
            R.id.btnList2 -> {
                atvCity.text = parseList2(readJSONFromAsset("test2.json")!!)


            }
            R.id.btnList3 -> {
                atvCity.text = parseList3(readJSONFromAsset("test3.json")!!)
            }
        }
    }

    fun readJSONFromAsset(fileName: String): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun parseList1(josnData: String): String {
        val sb: StringBuilder = StringBuilder()
        val jarray: JSONArray = JSONArray(josnData)
        for (x in 0 until jarray.length()) {
            val jsonObject: JSONObject = jarray[x] as JSONObject
            sb.append(jsonObject.optString("city") + "\n ")
        }

        return sb.toString()
    }

    fun parseList2(jsonData: String): String {
        val sb: StringBuilder = StringBuilder()
        val jarray: JSONArray = JSONArray(jsonData)
        for (x in 0 until jarray.length()) {

            val jsonObject: JSONObject = jarray[x] as JSONObject
            val friendsArray: JSONArray = jsonObject.optJSONArray("friends")
            for (y in 0 until friendsArray.length()) {
                val objectFriends: JSONObject = friendsArray[y] as JSONObject
                sb.append(objectFriends.optString("city") + "\n ")

            }

        }
        return sb.toString()
    }

    fun parseList3(jsonData: String): String {
        val sb: StringBuilder = StringBuilder()
        val jarray: JSONArray = JSONArray(jsonData)
        for (x in 0 until jarray.length()) {

            val jsonObject: JSONObject = jarray[x] as JSONObject
            val tagObject = jsonObject.optJSONObject("tags")
            val addressArray = tagObject.optJSONArray("address")
            for (y in 0 until addressArray.length()) {

                val objectAddress: JSONObject = addressArray[y] as JSONObject

                sb.append(objectAddress.optString("city") + "\n")
            }
        }

        return sb.toString()
    }
}
