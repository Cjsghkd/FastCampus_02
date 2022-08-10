package com.example.a30androidwithkotlie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.a30androidwithkotlie.ch12_model.Ch12_Book
import com.example.a30androidwithkotlie.ch12_model.Ch12_Review
import com.example.a30androidwithkotlie.databinding.ActivityCh12DetailBinding

class Ch12_DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCh12DetailBinding

    private lateinit var DB : Ch12_AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCh12DetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        DB = getAppDatabase(this)

        val model = intent!!.getParcelableExtra<Ch12_Book>("bookModel")

        binding.DetailTitleTextView.text = model?.title.orEmpty()
        binding.DetailDescriptionTextView.text = model?.description.orEmpty()

        Glide.with(binding.DetailCoverImageView.context)
            .load(model?.coverSmallUrl.orEmpty())
            .into(binding.DetailCoverImageView)

        Thread {
            val review = DB.reviewDao().getOneReview(model?.id?.toInt() ?: 0)
            runOnUiThread {
                binding.reviewEditText.setText(review?.review.orEmpty())
            }
        }.start()

        binding.saveButton.setOnClickListener {
            Thread {
                DB.reviewDao().saveReview(
                    Ch12_Review(
                        model?.id?.toInt() ?: 0,
                        binding.reviewEditText.text.toString()
                    )
                )
            }.start()
        }
    }
}