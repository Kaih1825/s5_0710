package com.example.s5_0710

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.s5_0710.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var b:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(b.root)
        var sp=getSharedPreferences("sp", Context.MODE_PRIVATE)
        if(sp.getBoolean("isLogin",false)){
            b.type.text="Name"
        }else{
            b.type.text="訪客"
            b.logout.visibility=View.GONE
        }

        b.menuIcon.setOnClickListener{
            b.drawer.open()
        }

        b.btnPerson.setOnClickListener {
            b.pI.imageTintList=ColorStateList.valueOf(resources.getColor(R.color.blue))
            b.pT.setTextColor(resources.getColor(R.color.blue))

            b.hI.imageTintList=ColorStateList.valueOf(Color.BLACK)
            b.hT.setTextColor(Color.BLACK)
        }

        b.btmHome.setOnClickListener {
            b.hI.imageTintList=ColorStateList.valueOf(resources.getColor(R.color.blue))
            b.hT.setTextColor(resources.getColor(R.color.blue))

            b.pI.imageTintList=ColorStateList.valueOf(Color.BLACK)
            b.pT.setTextColor(Color.BLACK)
        }

        b.logout.setOnClickListener {
            sp.edit().putBoolean("isLogin",false).apply()
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        }
    }
}