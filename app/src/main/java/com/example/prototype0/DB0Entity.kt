package com.example.prototype0

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table0")
data class DB0Entity (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "Column0Name") var Column0Name: String,
    @ColumnInfo(name = "Column1Name") var Column1Nam: String
        )