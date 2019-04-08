package com.example.hp.mvvmtestdemo.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hp.mvvmtestdemo.R
import com.example.hp.mvvmtestdemo.databinding.ActivityMainBinding
import com.example.hp.mvvmtestdemo.view.fragment.MusicFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 通过DataBindingUtil 获取 ActivityMainBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.music_fragment,MusicFragment.getInstance()).commit()
    }
}
