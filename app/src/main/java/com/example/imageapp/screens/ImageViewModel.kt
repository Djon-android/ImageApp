package com.example.imageapp.screens

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.imageapp.R
import com.example.imageapp.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageViewModel(application: Application) : AndroidViewModel(application) {

    private var app = application
    private val compositeDisposable = CompositeDisposable()
    var liveDataImage: MutableLiveData<MutableList<String>> = MutableLiveData()

    init {
        loadData()
    }

    fun loadData() {
        var disposable = ApiFactory.apiService.getAllImage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataImage.value = it
                Toast.makeText(getApplication(), app.getString(R.string.update_success), Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(getApplication(), "${it.message}", Toast.LENGTH_SHORT).show()
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}