package kr.co.example.uptory_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProductInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 표시할 레이아웃을 정의
        return inflater.inflate(R.layout.fragment_product_info, container, false)
    }
}
