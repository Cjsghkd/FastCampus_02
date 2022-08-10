package com.example.a30androidwithkotlie

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a30androidwithkotlie.ch12_dao.Ch12_HistoryDao
import com.example.a30androidwithkotlie.ch12_dao.Ch12_ReviewDao
import com.example.a30androidwithkotlie.ch12_model.Ch12_History
import com.example.a30androidwithkotlie.ch12_model.Ch12_Review
import com.example.a30androidwithkotlie.ch4_dao.HistoryDao
import com.example.a30androidwithkotlie.ch4_model.History

@Database(entities = [Ch12_History::class, Ch12_Review::class], version = 1)
abstract class Ch12_AppDatabase : RoomDatabase() {
    abstract fun historyDao() : Ch12_HistoryDao
    abstract fun reviewDao() : Ch12_ReviewDao
}