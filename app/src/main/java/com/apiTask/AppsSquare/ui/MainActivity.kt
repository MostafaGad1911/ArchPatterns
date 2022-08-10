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
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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

class MainActivity : AppCompatActivity(){
    lateinit var controller: NavController // don't forget to initialize

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }



    private fun initView(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.training_graph_container) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        controller = navHostFragment.navController
        controller.navigate(R.id.homeFragment)
    }


}