//package com.example.a30androidwithkotlie.ch12_dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.a30androidwithkotlie.ch12_model.Ch12_Review
//
//@Dao
//interface Ch12_ReviewDao {
//
//    @Query("SELECT * FROM ch12_review WHERE id == :id")
//    fun getOneReview(id : Int) : Ch12_Review
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveReview(review : Ch12_Review)
//}