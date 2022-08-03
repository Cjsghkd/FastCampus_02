package com.example.a30androidwithkotlie

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton


class Ch7_RecordButton (
    context : Context,
    attrs : AttributeSet
) : AppCompatImageButton(context, attrs) {

    fun updateIconWithState(state : Ch7_State) {
        when(state) {
            Ch7_State.BEFORE_RECORDING -> {
                setImageResource(R.drawable.ic_record_24)
            }
            Ch7_State.ON_RECORDING -> {
                setImageResource(R.drawable.ic_stop_24)
            }
            Ch7_State.AFTER_RECORDING -> {
                setImageResource(R.drawable.ic_play_arrow_24)
            }
            Ch7_State.ON_PLAYING -> {
                setImageResource(R.drawable.ic_stop_24)
            }
        }
    }
}