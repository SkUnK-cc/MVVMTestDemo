package com.example.hp.mvvmtestdemo.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hp.mvvmtestdemo.R
import com.example.hp.mvvmtestdemo.databinding.FragmentMusicBinding
import com.example.hp.mvvmtestdemo.viewmodel.ViewModelFragment

class MusicFragment : Fragment() {

    lateinit var fragmentBinding: FragmentMusicBinding
    lateinit var viewModelFragment: ViewModelFragment

    lateinit var adapter: MSongRecyclerAdapter

    companion object {
        val INSTANCE = MusicFragment()
        fun getInstance():MusicFragment{
            return INSTANCE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 通过 DataBindingUtil获得 binding实例
        fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_music,container,false)
        initView()
        return fragmentBinding.root
    }

    private fun initView() {
        adapter = MSongRecyclerAdapter(context)

        fragmentBinding.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        fragmentBinding.recyclerView.itemAnimator = DefaultItemAnimator()
        fragmentBinding.recyclerView.adapter = adapter

        viewModelFragment = ViewModelFragment(adapter)
        fragmentBinding.viewModel = viewModelFragment
    }
}