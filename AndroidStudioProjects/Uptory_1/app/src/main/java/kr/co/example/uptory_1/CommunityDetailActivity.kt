package kr.co.example.uptory_1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.ImageView
import android.widget.TextView
import kr.co.example.uptory_1.R

class CommunityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        // 게시글에 업로드된 이미지를 표시하는 ImageView
        val postImageView = findViewById<ImageView>(R.id.uploaded_image)

        // 서버에서 가져온 이미지 URL (예: 서버에 업로드된 이미지의 경로)
        val imageUrl = "https://your-server.com/uploaded-image.jpg"

        // Glide로 이미지 로드 및 표시
        Glide.with(this).load(imageUrl).placeholder(R.drawable.placeholder)  // 로딩 중 표시할 이미지
            .error(R.drawable.community_sample)  // 오류 시 표시할 이미지
            .into(postImageView)

        //팔로우 버튼 onclickListener
        val followTextView = findViewById<TextView>(R.id.follow_button)

        followTextView.setOnClickListener {
            if (followTextView.text == "+ 팔로우") {
                followTextView.text = "팔로우 중"
                followTextView.setBackgroundResource(R.drawable.following_button_background)
            } else {
                followTextView.text = "+ 팔로우"
                followTextView.setBackgroundResource(R.drawable.follow_button_background)
            }

    }
}}
