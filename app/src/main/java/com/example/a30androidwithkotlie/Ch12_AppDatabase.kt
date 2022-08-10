package com.example.a30androidwithkotlie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a30androidwithkotlie.ch12_dao.Ch12_HistoryDao
import com.example.a30androidwithkotlie.ch12_dao.Ch12_ReviewDao
import com.example.a30androidwithkotlie.ch12_model.Ch12_History
import com.example.a30androidwithkotlie.ch12_model.Ch12_Review
import com.example.a30androidwithkotlie.ch4_dao.HistoryDao
import com.example.a30androidwithkotlie.ch4_model.History

@Database(entities = [Ch12_History::class, Ch12_Review::class], version = 2)
abstract class Ch12_AppDatabase : RoomDatabase() {
    abstract fun historyDao() : Ch12_HistoryDao
    abstract fun reviewDao() : Ch12_ReviewDao
}

fun getAppDatabase(context : Context) : Ch12_AppDatabase {

    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE 'Ch12_Review' ('id' INTEGER, 'review' TEXT," + "PRIMARY KEY('id'))")
        }
    }

    return Room.databaseBuilder(
        context,
        Ch12_AppDatabase::class.java,
        "BookSearchDB"
    ).addMigrations(migration_1_2)
        .build()
}