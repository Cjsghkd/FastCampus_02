package com.example.a30androidwithkotlie.ch12_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ch12_History (
    @PrimaryKey val uid : Int?,
    @ColumnInfo(name = "keyword") val keyword : String?
)