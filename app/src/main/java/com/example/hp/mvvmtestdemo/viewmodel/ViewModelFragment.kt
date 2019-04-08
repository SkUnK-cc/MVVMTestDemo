package com.example.hp.mvvmtestdemo.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.view.View
import com.example.hp.mvvmtestdemo.api.RetrofitFactory
import com.example.hp.mvvmtestdemo.model.result.QueryMergeResp
import com.example.hp.mvvmtestdemo.view.fragment.MSongRecyclerAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ViewModelFragment(adapter: MSongRecyclerAdapter) : BaseObservable(){
    var adapter: MSongRecyclerAdapter = adapter

    var search_word = ObservableField<String>("月光")

    lateinit var errorInfoLayoutVisibility: ObservableField<Int>

    init {
        initData()
        getSong()
    }

    private fun getSong() {
        RetrofitFactory.provideBaiduApi()
                .queryMerge(search_word.get(), 1, 50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<QueryMergeResp> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(queryMergeResp: QueryMergeResp) {
                        if (queryMergeResp != null && queryMergeResp!!.isValid()) {

                            var list = queryMergeResp.result.song_info.song_list
                            adapter.updateData(list)
                            adapter.notifyDataSetChanged()

                            errorInfoLayoutVisibility.set(View.GONE)
                        } else {

                        }
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        errorInfoLayoutVisibility.set(View.VISIBLE)
                    }
                    override fun onComplete() {
                    }
                })
    }

    private fun initData() {
        errorInfoLayoutVisibility = ObservableField()
        errorInfoLayoutVisibility.set(View.GONE)
    }
}