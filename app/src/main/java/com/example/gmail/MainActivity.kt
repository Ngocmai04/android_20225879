package com.example.gmail

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.dssv.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmail)
        val emails = ArrayList<Email>()

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "Facebook",
                "Trương Ngọc Mai ơi, bạn có 3 thông báo mới",
                "Hôm nay",
                "Bạn có 3 tương tác mới từ bạn bè. Nhấn để xem chi tiết và cập nhật hoạt động."
            )
        )

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "Zalo",
                "Tin nhắn chưa đọc từ nhóm Lớp Mobile",
                "Hôm nay",
                "Nhóm Lập Trình Mobile vừa gửi tài liệu mới. Hãy kiểm tra để không bỏ lỡ deadline nhé."
            )
        )

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "Google",
                "Xác minh bảo mật tài khoản của bạn",
                "Hôm qua",
                "Hoạt động đăng nhập bất thường đã được phát hiện trên tài khoản của bạn. Vui lòng xác minh ngay."
            )
        )

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "Shopee",
                "Mai ơi, voucher giảm 50% đang chờ bạn!",
                "Thứ Hai",
                "Flash Sale sẽ diễn ra vào tối nay. Nhận voucher giảm giá ngay trước khi hết lượt."
            )
        )

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "FPT Polytechnic",
                "Thông báo lịch thi kết thúc học phần",
                "Thứ Hai",
                "Mời sinh viên kiểm tra lịch thi chính thức đã được cập nhật trên hệ thống đào tạo."
            )
        )

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "GitHub",
                "New login from Chrome Windows",
                "Chủ Nhật",
                "A new sign-in to your GitHub account was detected from Windows. If this wasn’t you, secure your account now."
            )
        )

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "TikTok",
                "Mai, bạn có video mới từ followings",
                "Chủ Nhật",
                "Những người bạn theo dõi đã đăng nhiều video mới hôm nay. Vuốt lên để xem ngay."
            )
        )

        emails.add(
            Email(
                R.drawable.ic_email_i,
                "LinkedIn",
                "Việc làm phù hợp với ngành CNTT",
                "Thứ Bảy",
                "LinkedIn đề xuất 5 cơ hội nghề nghiệp phù hợp với hồ sơ của bạn. Ứng tuyển ngay để không bỏ lỡ!"
            )
        )
        val listView: ListView = findViewById(R.id.listView)
        val emailAdapter = EmailAdapter(this, emails)
        listView.adapter = emailAdapter
    }
}
