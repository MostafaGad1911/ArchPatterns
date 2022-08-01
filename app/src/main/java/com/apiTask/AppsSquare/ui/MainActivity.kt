package com.apiTask.AppsSquare.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apiTask.AppsSquare.Adapter.DataAdapter
import com.apiTask.AppsSquare.Api.Calls
import com.apiTask.AppsSquare.Api.Conn
import com.apiTask.AppsSquare.Model.Data
import com.apiTask.AppsSquare.Model.DataModel
import com.apiTask.AppsSquare.PostsPresenter.PostPresenter
import com.apiTask.AppsSquare.PostsPresenter.PostsVew
import com.apiTask.AppsSquare.R
import com.apiTask.AppsSquare.ViewModel.PostsVM
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity()  , PostsVew{
    lateinit var myRecycler : RecyclerView
    lateinit var layoutManager: GridLayoutManager
    lateinit var data : ArrayList<Data>
    lateinit var dataAdapter : DataAdapter
    lateinit var constraintLayout : ConstraintLayout
    lateinit var progressBar: ProgressBar

    val postsVM:PostsVM  by viewModels()
    lateinit var postsPresenter: PostPresenter

    lateinit var flow: Flow<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        postsPresenter = PostPresenter(this)
        postsPresenter.getPosts()

//        postsVM.getMyPosts()
//        postsVM.postsLiveData.observe(this , Observer {
//            data = it
//            install()
//            constraintLayout.visibility = View.VISIBLE
//            progressBar.visibility = View.GONE
//        })
//        postsVM.errorLiveData.observe(this , Observer {
//            Toast.makeText(this , it , Toast.LENGTH_LONG).show()
//        })
    }


    override fun onPause() {
        super.onPause()
        postsVM.postsLiveData.removeObservers(this)
        postsVM.errorLiveData.removeObservers(this)
    }


    fun initView(){
        myRecycler=findViewById(R.id.my_recycler)
        progressBar = findViewById(R.id.progress)
        constraintLayout = findViewById(R.id.layout)
    }

    fun install(){

        layoutManager = GridLayoutManager(this,2)
        myRecycler.layoutManager = layoutManager
        dataAdapter = DataAdapter(data,this)
        myRecycler.adapter = dataAdapter
    }

    override fun setPosts(dataList: ArrayList<Data>) {
        data = dataList
        install()
        constraintLayout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

    }

    override fun setError(errorMessage: String) {
        Toast.makeText(this , errorMessage , Toast.LENGTH_LONG).show()
    }
}