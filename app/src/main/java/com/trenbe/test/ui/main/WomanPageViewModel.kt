package com.trenbe.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trenbe.test.TestApplication.Companion.trenbeApiServer
import com.trenbe.test.network.vo.Categori
import com.trenbe.test.network.vo.CategoriResposeVo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class WomanPageViewModel : ViewModel() {

    var categoryList: MutableLiveData<List<Categori>>? = null
    private var disposable: Disposable? = null


    fun getList(): LiveData<List<Categori>> {
        if (categoryList == null) {
            categoryList = MutableLiveData()
            loadCategories()
        }
        return categoryList as MutableLiveData<List<Categori>>
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    private fun loadCategories() {
        disposable = trenbeApiServer.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: CategoriResposeVo ->
                categoryList!!.value = t.categories

                categoryList?.value?.forEach {
                    it.apply {
                        println("name = $name")
                        images.apply {
                            println("x1 = $_1x")
                            println("x2 = $_2x")
                            println("x3 = $_3x")
                        }
                    }
                }

            }, { t: Throwable? -> t!!.printStackTrace() })
    }
}