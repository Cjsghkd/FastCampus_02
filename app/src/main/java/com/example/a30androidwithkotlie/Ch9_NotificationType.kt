package com.example.a30androidwithkotlie

enum class Ch9_NotificationType(val title : String, val id : Int) {
    NORMAL("일반 알림", 0),
    EXPANDABLE("확장형 알림", 1),
    CUSTOM("커스텀 알림", 3)
}