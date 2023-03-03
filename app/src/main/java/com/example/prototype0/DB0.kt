package com.example.prototype0

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DB0Entity::class], version = 1)
abstract class DB0: RoomDatabase() {
    abstract fun getDao(): DB0Dao
    companion object{
        fun getDB0(context: Context) : DB0{
            return Room.databaseBuilder(context.applicationContext, DB0::class.java, "DB0").build()
        }
    }
}