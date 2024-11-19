package kr.co.example.uptory_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatAdapter(private val chatList: List<ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatMessageTextView: TextView = itemView.findViewById(R.id.chatMessage)
        val chatTimeTextView: TextView = itemView.findViewById(R.id.chatTime)
        val chatDateTextView: TextView = itemView.findViewById(R.id.chatDate)
        val profileImageView: ImageView = itemView.findViewById(R.id.profileImage)
        val usernameTextView: TextView = itemView.findViewById(R.id.username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatMessage = chatList[position]

        // 메시지 내용 표시
        holder.chatMessageTextView.text = chatMessage.message

        // 보낸 시간 표시
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        holder.chatTimeTextView.text = timeFormat.format(chatMessage.timestamp)

        // 날짜 표시: 첫 메시지이거나, 날짜가 바뀔 때만 표시
        if (position == 0 || !isSameDay(chatMessage.timestamp, chatList[position - 1].timestamp)) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            holder.chatDateTextView.text = dateFormat.format(chatMessage.timestamp)
            holder.chatDateTextView.visibility = View.VISIBLE
        } else {
            holder.chatDateTextView.visibility = View.GONE
        }
        // 시간 단위로 프로필 사진과 이름 표시
        if (position == 0 || !isSameHour(chatMessage.timestamp, chatList[position - 1].timestamp)) {
            // 첫 번째 메시지거나 시간이 바뀌면 프로필과 이름을 표시
            holder.profileImageView.visibility = View.VISIBLE
            holder.usernameTextView.visibility = View.VISIBLE
            holder.usernameTextView.text = chatMessage.username // 사용자 이름 설정


        } else {
            holder.profileImageView.visibility = View.GONE
            holder.usernameTextView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    // 날짜가 같은지 비교하는 함수
    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return format.format(date1) == format.format(date2)
    }

    // 같은 시간대(시간 단위)인지 확인하는 함수
    private fun isSameHour(date1: Date, date2: Date): Boolean {
        val format = SimpleDateFormat("yyyyMMddHH", Locale.getDefault())
        return format.format(date1) == format.format(date2)
    }
}


