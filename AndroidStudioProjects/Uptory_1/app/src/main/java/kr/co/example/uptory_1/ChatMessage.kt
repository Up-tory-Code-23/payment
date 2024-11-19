package kr.co.example.uptory_1

import java.util.Date

data class ChatMessage(
    val message: String,
    val timestamp: Date,
    val username: String = "Hi", // 사용자 이름 추가
    val profileUrl: String = "Anonymous" // 프로필 이미지 URL 추가 (로컬 리소스 사용 시 res ID로 설정 가능)

)