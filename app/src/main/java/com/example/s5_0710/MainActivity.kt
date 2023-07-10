package com.example.s5_0710

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.s5_0710.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var b:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        var sp=getSharedPreferences("sp",Context.MODE_PRIVATE)
        if(sp.getBoolean("isLogin",false)){
            startActivity(Intent(this,HomeActivity::class.java))
            finishAffinity()
        }

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

        b.login.setOnClickListener {
            if(b.email.text.toString()=="abc123@mail.com" && b.pwd.text.toString()=="Asdf456!"){
                var sp=getSharedPreferences("sp",Context.MODE_PRIVATE).edit()
                sp.putBoolean("isLogin",true)
                sp.apply()
                startActivity(Intent(this,HomeActivity::class.java))
            }else{
                b.login.text="登入失敗"
                b.login.setTextColor(Color.RED)
            }
        }

        b.noName.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        b.reg.setOnClickListener {
            startActivity(Intent(this,RegActivity::class.java))
        }
    }
}