package com.andy.modules

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andy.modules.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mainActivityDelegate: MainActivityDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivityDelegate = MainActivityDelegate(
            activity = this,
            binding = binding
        )
    }
}