//package com.example.a30androidwithkotlie.ch12_dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import com.example.a30androidwithkotlie.ch12_model.Ch12_History
//
//@Dao
//interface Ch12_HistoryDao {
//
//    @Query("SELECT * FROM ch12_history")
//    fun getAll() : List<Ch12_History>
//
//    @Insert
//    fun  insertHistory(history : Ch12_History)
//
//    @Query("DELETE FROM ch12_history WHERE keyword == :keyword")
//    fun delete(keyword : String)
//}