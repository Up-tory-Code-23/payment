package kr.co.example.uptory_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)  // 해당 레이아웃 파일 연결

        // 1. RecyclerView 초기화
        val recyclerView = findViewById<RecyclerView>(R.id.message_list)

        // 2. 레이아웃 매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 구분선 추가
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        val dividerDrawable = ContextCompat.getDrawable(this, R.drawable.list_divider)
        if (dividerDrawable != null) {
            dividerItemDecoration.setDrawable(dividerDrawable)
        }
        recyclerView.addItemDecoration(dividerItemDecoration)

        // 3. 데이터 설정
        val messageList = listOf(
            Message("Nice. I don't know why people get all worked up about Hawaiian pizza.", "9:36 AM", "Stephen Yustiono", R.drawable.ic_profile_placeholder),
            Message("Sad fact: you cannot search for a gif of the word 'gif', just gives you gifs.", "9:28 AM", "Erin Steed", R.drawable.ic_profile_placeholder),
            Message("Maybe email isn't the best form of communication.", "9:20 AM", "Daisy Tinsley", R.drawable.ic_profile_placeholder),
            Message("Tabs make way more sense than spaces.", "9:00 AM", "Zach Friedman", R.drawable.ic_profile_placeholder)
        )

        // 4. 어댑터 설정
        val adapter = MessageAdapter(messageList)
        recyclerView.adapter = adapter
    }
}
