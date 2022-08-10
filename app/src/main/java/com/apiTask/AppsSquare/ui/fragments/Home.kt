package com.apiTask.AppsSquare.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apiTask.AppsSquare.Adapter.DataAdapter
import com.apiTask.AppsSquare.Model.Data
import com.apiTask.AppsSquare.PostsPresenter.PostPresenter
import com.apiTask.AppsSquare.PostsPresenter.PostsVew
import com.apiTask.AppsSquare.R
import com.apiTask.AppsSquare.ViewModel.PostsVM
import kotlinx.coroutines.flow.Flow

class Home : Fragment() , PostsVew {

    private lateinit var myRecycler : RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var data : ArrayList<Data>
    private lateinit var dataAdapter : DataAdapter
    private lateinit var constraintLayout : ConstraintLayout
    private lateinit var progressBar: ProgressBar

    private val postsVM: PostsVM by viewModels()
    lateinit var postsPresenter: PostPresenter

    lateinit var flow: Flow<Int>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        postsPresenter = PostPresenter(this)
        postsPresenter.getPosts()
        Toast.makeText(requireActivity() , "Test" , Toast.LENGTH_LONG).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }
    override fun onPause() {
        super.onPause()
        postsVM.postsLiveData.removeObservers(this)
        postsVM.errorLiveData.removeObservers(this)
    }


    private fun initView(vew:View){
        myRecycler= vew?.findViewById(R.id.my_recycler)!!
        progressBar = vew?.findViewById(R.id.progress)!!
        constraintLayout = vew?.findViewById(R.id.layout)!!
    }

    private fun install(){

        layoutManager = GridLayoutManager(requireActivity(),2)
        myRecycler.layoutManager = layoutManager
        dataAdapter = DataAdapter(data,requireActivity())
        myRecycler.adapter = dataAdapter
    }

    override fun setPosts(dataList: ArrayList<Data>) {
        data = dataList
        install()
        constraintLayout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

    }

    override fun setError(errorMessage: String) {
        Toast.makeText(requireActivity() , errorMessage , Toast.LENGTH_LONG).show()
    }

}