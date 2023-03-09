package com.sagul.wowo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.apply {
            systemUiVisibility=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }



    }
}