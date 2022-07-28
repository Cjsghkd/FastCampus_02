package com.example.a30androidwithkotlie

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a30androidwithkotlie.ch4_dao.HistoryDao
import com.example.a30androidwithkotlie.ch4_model.History

@Database(entities = [History::class], version = 1)
abstract class Ch4_AppDatabase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
}