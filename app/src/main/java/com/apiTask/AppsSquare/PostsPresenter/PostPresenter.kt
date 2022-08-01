package com.apiTask.AppsSquare.PostsPresenter

import com.apiTask.AppsSquare.Api.Conn
import com.apiTask.AppsSquare.Model.DataModel
import com.apiTask.AppsSquare.Model.LoginData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostPresenter(var postsVew: PostsVew) {

    fun getPosts(){
        Conn.calls.getData().enqueue(object : Callback<DataModel>{
            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                when(response?.code()){
                    200 -> {
                        postsVew.setPosts(dataList = response.body().data)
                    }

                    else -> {
                        postsVew.setError(errorMessage = response?.errorBody()?.string().toString())
                    }
                }
            }

            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                postsVew.setError(errorMessage = t?.message?.toString().toString())
            }

        })
    }

    fun login(loginData: LoginData){
        Conn.calls.login(loginData = loginData).enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            }

        })
    }
}