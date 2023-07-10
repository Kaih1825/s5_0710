package com.example.s5_0710

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.widget.addTextChangedListener
import com.example.s5_0710.databinding.ActivityRegBinding
import java.util.Locale

class RegActivity : AppCompatActivity() {
    lateinit var b:ActivityRegBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityRegBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.email.addTextChangedListener {
            var txt=it.toString()
            if(!Regex("^[A-Z,a-z,0-9]+@[A-Z,a-z,0-9]+\\.[A-Z,a-z,0-9]{2,4}$").matches(txt)){
                b.email.error="格式錯誤"
            }else{
                b.email.error=null
            }
        }

        b.pwd.addTextChangedListener {
            var txt=it.toString()
            if(!(txt.contains(Regex("[A-Z]")) && txt.contains(Regex("[a-z]")) && txt.contains(Regex("[0-9]")) &&txt.contains(Regex("[\\W]")))  || txt.contains(" ") || txt.length<8 || txt.length>15){
                b.pwd.error="格式錯誤"
            }else{
                b.pwd.error=null
            }
        }


        b.country.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var countrys= arrayOf("zh-Hant","en-US")
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(countrys[position]))
                getSharedPreferences("sp",Context.MODE_PRIVATE).edit().putString("lang",countrys[position]).apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
            }

        })


    }
}