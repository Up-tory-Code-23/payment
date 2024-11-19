package kr.co.example.uptory_1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class  ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // RecyclerView 초기화
        chatRecyclerView = findViewById(R.id.chatRecyclerView)

        // RecyclerView 레이아웃 매니저 설정 (LinearLayoutManager 사용)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)

        // 툴바 설정
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 툴바 기본 제목 숨기기
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // 툴바 제목을 TextView로 설정
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Hi" // 원하는 제목으로 설정

        // 뒤로가기 화살표 설정
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow) // 뒤로가기 아이콘 설정

        // 어댑터에 들어갈 데이터 설정 (ChatMessage로 변경)
        val chatList = listOf(
            ChatMessage("안녕하세요", Date(System.currentTimeMillis() - 10000000)), // 10,000초 전
            ChatMessage("어떻게 도와드릴까요?", Date(System.currentTimeMillis() - 5000000)), // 5,000초 전
            ChatMessage("메시지를 입력해 주세요!", Date()) // 현재 시간
        )

        // 어댑터 생성 및 RecyclerView에 연결
        chatAdapter = ChatAdapter(chatList)
        chatRecyclerView.adapter = chatAdapter

        // Edge-to-Edge 시스템 창 관리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    // 메뉴 아이콘 추가 (오른쪽에 삼선 아이콘)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_menu, menu)
        return true
    }

    // 메뉴 아이템 클릭 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // 뒤로가기 버튼 클릭 시 동작
                finish() // 또는 네비게이션 처리
                true
            }
            R.id.menu_settings -> {
                // 삼선 메뉴 아이콘 클릭 시 동작
                Toast.makeText(this, "설정 아이콘 클릭됨", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
