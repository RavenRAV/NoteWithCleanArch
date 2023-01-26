package com.example.cleanarchitecture.domain.model

data class Note(
    val id: Int = DEFAULT_ID,
    val title: String,
    val description: String,
    val createAt: String
){
    companion object{
        const val DEFAULT_ID = 0
    }
}