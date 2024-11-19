package kr.co.example.uptory_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.example.uptory_1.databinding.FragmentReviewBinding
import kr.co.example.uptory_1.databinding.BottomSheetPaymentBinding
import kr.co.example.uptory_1.databinding.BottomSheetsOptionsBinding

class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰 바인딩 설정
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView 설정
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(context)

        // 샘플 리뷰 데이터 생성
        val reviews = listOf(
            Review(
                profileImageUrl = "https://example.com/user1.png",
                nickname = "pe**",
                purchaseDate = "2024.06.18",
                rating = 4.5f,
                reviewText = "무게감 가벼운 편이에요 사이즈 정사이즈에요 퀄리티 보통이에요",
                images = listOf(
                    "https://example.com/product1_1.png",
                    "https://example.com/product1_2.png"
                ),
                options = "옵션: 베이지 - 돼지코 추가 안함"
            ),
            Review(
                profileImageUrl = "https://example.com/user2.png",
                nickname = "강**",
                purchaseDate = "2024.10.28",
                rating = 5.0f,
                reviewText = "색깔도 너무 예쁘고 수납할 수 있는 칸도 3곳이나 있어서 완전 맘에 들어요!",
                images = listOf(
                    "https://example.com/product2_1.png"
                ),
                options = "옵션: 3. Sky Blue - ONE SIZE"
            )
        )

        // 어댑터 설정
        val adapter = ReviewAdapter(reviews)
        binding.reviewRecyclerView.adapter = adapter

        return view
    }

    private fun showOptionsDialog() {
        val optionsBinding = BottomSheetsOptionsBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(optionsBinding.root)

        optionsBinding.confirmButton.setOnClickListener {
            bottomSheetDialog.dismiss()
            showPaymentOptions() // confirmButton 클릭 시 결제 창 띄우기
        }

        bottomSheetDialog.show()
    }

    private fun showPaymentOptions() {
        val bottomSheetBinding = BottomSheetPaymentBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        // 예: 배송지, 쿠폰/포인트, 결제 수단 등을 설정하는 로직 추가
        bottomSheetBinding.shippingAddressTextView.text = "회원의 배송지 정보"
        bottomSheetBinding.couponTextView.text = "사용 가능한 쿠폰 정보"
        bottomSheetBinding.paymentMethodTextView.text = "결제 수단 선택"
        bottomSheetBinding.totalAmountTextView.text = "총 결제 금액"

        bottomSheetBinding.confirmPaymentButton.setOnClickListener {
            // 결제 처리 로직 추가
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// 데이터 클래스 정의
data class Review(
    val profileImageUrl: String,
    val nickname: String,
    val purchaseDate: String,
    val rating: Float,
    val reviewText: String,
    val images: List<String>,
    val options: String
)

// RecyclerView 어댑터
class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int = reviews.size

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review) {
            // 리뷰 데이터 바인딩 처리
            // 예시: 프로필 이미지, 닉네임, 구매일, 리뷰 텍스트 등
        }
    }
}
