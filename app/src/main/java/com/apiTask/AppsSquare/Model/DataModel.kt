package com.apiTask.AppsSquare.Model

data class DataModel(
    val count: Int,
    val `data`: ArrayList<Data>,
    val message: String,
    val status: Boolean
)

data class Data(
    val id: Int,
    val image: String,
    val name: String,
    val price: Int,
    val quantity: Int
)