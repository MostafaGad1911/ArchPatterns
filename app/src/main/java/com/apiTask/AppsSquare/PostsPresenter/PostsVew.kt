package com.apiTask.AppsSquare.PostsPresenter

import com.apiTask.AppsSquare.Model.Data

interface PostsVew {
    fun setPosts(dataList:ArrayList<Data>)
    fun setError(errorMessage:String)
}