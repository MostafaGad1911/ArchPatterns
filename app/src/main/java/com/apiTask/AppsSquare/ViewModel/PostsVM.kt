package com.apiTask.AppsSquare.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apiTask.AppsSquare.Api.Conn
import com.apiTask.AppsSquare.Model.Data
import com.apiTask.AppsSquare.Model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsVM : ViewModel() {

    var postsMD: MutableLiveData<ArrayList<Data>> = MutableLiveData()
    val postsLiveData: LiveData<ArrayList<Data>>
        get() = postsMD

    var errorMessage:MutableLiveData<String> = MutableLiveData()
    val errorLiveData:LiveData<String>
        get() = errorMessage

    fun getMyPosts(){
        val conn =  Conn.calls
        val call = conn.getData()
        call.enqueue(object : Callback<DataModel>{
            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                when(response?.code()){
                    200 -> {
                        postsMD.postValue(response.body().data!!)
                    }

                    else -> {
                        errorMessage.postValue(response?.errorBody()?.string())
                    }
                }
            }

            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                errorMessage.postValue(t?.message)
            }

        })
    }

}