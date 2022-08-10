package com.example.a30androidwithkotlie

data class Ch11_AlarmDisplayModel(
    val hour : Int,
    val minute : Int,
    var onOff : Boolean
) {
    val timeText : String
        get() {
            val h = "%02d".format(if (hour < 12) hour else hour - 12)
            val m = "%02d".format(minute)

            return "$h:$m"
        }

    val ampmText : String
        get() {
            return if (hour < 12) "AM" else "PM"
        }

    fun makeDataforDB() : String {
        return "$hour:$minute"
    }
}
