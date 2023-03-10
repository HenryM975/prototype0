package com.example.prototype0

import android.content.ClipData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DB0Dao {
    @Insert
    fun AddItem(item: DB0Entity)
    @Query("SELECT * FROM table0")
    fun GetAll(): Flow<List<DB0Entity>>//!!!
}