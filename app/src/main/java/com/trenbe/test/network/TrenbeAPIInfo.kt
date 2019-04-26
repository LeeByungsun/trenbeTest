package com.trenbe.test.network

import com.trenbe.test.network.vo.CategoriResposeVo
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by bslee on 2019-3-10
 */

interface TrenbeAPIInfo {

    //posts 가져오기
    @GET("categories.json")
    fun getList(): Observable<CategoriResposeVo>


    companion object {
        //server Url
        val BASE_URL = "http://52.79.137.193:7010/Images/"
    }
}
