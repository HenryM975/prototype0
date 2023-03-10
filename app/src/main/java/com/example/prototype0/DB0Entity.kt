package com.example.prototype0

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "table0")
data class DB0Entity (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    //@PrimaryKey val uid: Int, //android dok
    @ColumnInfo(name = "Column0") var Column0: String?,
    @ColumnInfo(name = "Column1") var Column1: String?

        )/*{
    //here
    @Ignore lateinit var overview: String//https://stackoverflow.com/questions/44485631/room-persistence-errorentities-and-pojos-must-have-a-usable-public-constructor
}*/