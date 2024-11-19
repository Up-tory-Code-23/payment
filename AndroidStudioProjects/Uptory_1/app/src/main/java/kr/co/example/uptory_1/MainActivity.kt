package kr.co.example.uptory_1


import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.example.uptory_1.databinding.BottomSheetPaymentBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_product_detail.xml을 레이아웃으로 설정
        supportActionBar?.hide()
        setContentView(R.layout.activity_product_detail)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val buyButton = findViewById<Button>(R.id.buyButton)

        // ViewPager와 TabLayout 연결
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "상품정보"
                1 -> tab.text = "리뷰"
                2 -> tab.text = "사이즈"
                3 -> tab.text = "문의"
            }
        }.attach()

        // product_original_price 텍스트에 취소선을 설정
        val originalPriceTextView = findViewById<TextView>(R.id.product_original_price)
        originalPriceTextView.paintFlags =
            originalPriceTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        buyButton.setOnClickListener {
            showOptionsDialog()
        }
    }

    class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 4 // 총 4개의 탭 (상품정보, 리뷰, 사이즈, 문의)
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ProductInfoFragment() // 상품정보 프래그먼트
                1 -> ReviewFragment()      // 리뷰 프래그먼트
                2 -> SizeFragment()        // 사이즈 프래그먼트
                3 -> InquiryFragment()     // 문의 프래그먼트
                else -> ProductInfoFragment()
            }
        }
    }

    private fun showOptionsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheets_options, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(dialogView)

        // dialogView를 사용해 confirmButton, sizeSpinner, colorSpinner 등을 참조
        val confirmButton = dialogView.findViewById<Button>(R.id.confirmButton)
        val sizeSpinner = dialogView.findViewById<Spinner>(R.id.sizeSpinner)
        val colorSpinner = dialogView.findViewById<Spinner>(R.id.colorSpinner)


        confirmButton.setOnClickListener {
            Log.d("OptionsDialog", "Confirm button clicked") // 로그 추가

            val selectedSize = sizeSpinner.selectedItem.toString()
            val selectedColor = colorSpinner.selectedItem.toString()

            // 선택된 옵션을 처리하는 코드 추가
            Log.d("OptionsDialog", "Selected size: $selectedSize, Selected color: $selectedColor")

            // 다이얼로그 닫기 전에 다음 다이얼로그를 미리 준비하기
            bottomSheetDialog.dismiss()

            // 새로운 창(결제 옵션)을 띄우는 메서드 호출
            showPaymentOptions()
        }


        bottomSheetDialog.show()
    }
    private fun showPaymentOptions() {
        val intent = Intent(this, DefaultPaymentActivity::class.java)
        startActivity(intent)
    }
}
