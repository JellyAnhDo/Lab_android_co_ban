package Lab07;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.TaiKhoan;
import java.util.ArrayList;

import Lab06.SearchLocationActivity;

public class AccountInforActivity extends AppCompatActivity {
    TextView TextAccountInfor;
    Button CallButton, AddressButton;
    String phoneNumber = "", diaChi = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_infor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextAccountInfor = (TextView) findViewById(R.id.TextAccountInfor);
        CallButton = (Button) findViewById(R.id.btn_Call);
        AddressButton = (Button) findViewById(R.id.btn_diaChi);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            TaiKhoan selectedTaiKhoan = (TaiKhoan) intent.getExtras().get("taikhoan");

            if (selectedTaiKhoan != null) {
                String hoTen = selectedTaiKhoan.getHoTen();
                phoneNumber = selectedTaiKhoan.getSoDienThoai();
                String userName = selectedTaiKhoan.getUserName();
                String password = selectedTaiKhoan.getPassword();
                String ngaySinh = selectedTaiKhoan.getNgaySinh();
                diaChi = selectedTaiKhoan.getDiaChi();

                String taiKhoanInfo = "Tên tài khoản: " + hoTen
                        + "\nSố điện thoại: " + phoneNumber
                        + "\nNgày sinh: " + ngaySinh
                        + "\nĐịa chỉ: " + diaChi;
                TextAccountInfor.setText(taiKhoanInfo);
            }
        }

        CallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo intent để mở phần quay số của điện thoại
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(dialIntent);
            }
        });

        AddressButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                if (!diaChi.isEmpty()) {
                    Uri uri = Uri.parse("geo:0, 0?q="+Uri.encode(diaChi));
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(AccountInforActivity.this, "Không tìm thấy ứng dụng để mở bản đồ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AccountInforActivity.this, "Làm ơn nhập địa chỉ của bạn!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}