package kr.co.example.uptory_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private val messageList: List<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    // ViewHolder 클래스 정의
    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileImageView: ImageView = view.findViewById(R.id.profile_image)
        val messageTextView: TextView = view.findViewById(R.id.message_text)
        val timeTextView: TextView = view.findViewById(R.id.time_text)
        val nameTextView: TextView = view.findViewById(R.id.name_text)

    }

    // 레이아웃 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    // 데이터와 뷰를 연결하는 함수
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.messageTextView.text = message.messageText
        holder.timeTextView.text = message.time
        holder.nameTextView.text = message.name
        holder.profileImageView.setImageResource(message.profileImageResId)

    }

    // 아이템 수 반환
    override fun getItemCount(): Int {
        return messageList.size
    }
}
