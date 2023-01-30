package com.example.cleanarchitecture.domain.model

import android.os.Parcelable
import java.io.Serializable

data class Note(
    val id: Int = DEFAULT_ID,
    val title: String,
    val description: String,
    val createAt: Long
): Serializable{
    companion object{
        const val DEFAULT_ID = 0
    }
}