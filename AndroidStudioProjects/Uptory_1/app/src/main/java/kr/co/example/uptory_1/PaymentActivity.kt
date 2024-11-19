package kr.co.example.uptory_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.Payload
import kr.co.example.uptory_1.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 배송지 정보 설정 (예시)
        binding.shippingAddress.text = "서울 노원구 동일로207길 186, ○○○아파트 101동 101호"
        binding.userName.text = "김유진"
        binding.userPhone.text = "010-1234-5678"

        // 결제 금액 정보 (예시 데이터)
        binding.totalAmount.text = "41,800원"
        binding.discountAmount.text = "-15,200원"
        binding.deliveryFee.text = "무료배송"
        binding.finalAmount.text = "41,800원"

        // 결제하기 버튼 클릭 시 이벤트
        binding.confirmPaymentButton.setOnClickListener {
            val payload = Payload().apply {
                setApplicationId("673af35386fd08d2213fc719") // Bootpay Application ID
                setOrderName("테스트 결제") // 결제 이름
                setPrice(1000.0) // 결제 금액
            }

            CoroutineScope(Dispatchers.IO).launch {
                Bootpay.init(this@PaymentActivity)
                    .setPayload(payload)
                    .setEventListener(object : BootpayEventListener {
                        override fun onDone(data: String) {
                            Log.d("bootpay", "결제 완료: $data")
                        }

                        override fun onCancel(data: String) {
                            Log.d("bootpay", "결제 취소: $data")
                        }

                        override fun onError(data: String) {
                            Log.d("bootpay", "결제 실패: $data")
                        }

                        override fun onClose() {
                            Bootpay.removePaymentWindow() // 결제 창 닫기
                        }

                        override fun onConfirm(data: String): Boolean {
                            Log.d("bootpay", "결제 확인: $data")
                            return true
                        }

                        override fun onIssued(data: String) {
                            Log.d("bootpay", "결제 진행 중: $data")
                        }
                    }).requestPayment()

            }
        }


    }
}
