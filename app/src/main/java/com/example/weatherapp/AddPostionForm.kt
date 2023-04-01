package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import module.ApiClient
import module.Temp
import kotlin.properties.Delegates


class AddPostionForm : AppCompatActivity() {

    private lateinit var tempsState :String
    private lateinit var temp:Temp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_position_form)

        var toolbar = supportActionBar
        toolbar!!.title = "Form"
        toolbar.setDisplayHomeAsUpEnabled(true)


        val spinner = findViewById<Spinner>(R.id.spinner)
        val opts = mapOf("Sunny" to "","Rainy" to "","Cloudy" to "","Snow" to "")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,opts.keys.toList())
        spinner.adapter=adapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedOption = opts.toList()[position].first
                tempsState=selectedOption
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            temp.temps = tempsState
            temp.longitude = 0.0
            temp.latitude = 0.0
            temp.temperature = 0.0
            addTemp(temp)
        }

        //addTemp(temp)


    }

    private fun addTemp(tmp: Temp){
        GlobalScope.launch(Dispatchers.Main){
            try {
                val resp = ApiClient.apiService.createTemp(tmp)
                if (resp.isSuccessful && resp.body() != null) {
                    val content = resp.body()
                    Toast.makeText(
                        this@AddPostionForm,
                        "Added successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    //finish()
                } else {
                    Toast.makeText(
                        this@AddPostionForm,
                        "Error Occurred: ${resp.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (e: Exception){
                Toast.makeText(
                    this@AddPostionForm,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}