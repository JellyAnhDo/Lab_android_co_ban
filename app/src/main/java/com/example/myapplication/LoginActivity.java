package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Lab10.ProfileActivity;
import Lab10.UserPreferences;

public class LoginActivity extends AppCompatActivity {

    EditText userNameEditText, passwordEditText;
    Button loginButton;
    TextView hoTenTextView, soDienThoaiTextView;
    List<TaiKhoan> taiKhoanList = new ArrayList<>();
    private UserPreferences userPreferences;
    private static final String FILE_NAME = "account_data.txt";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_in2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo các thành phần
        init();

        // Thêm dữ liệu vào list tài khoản
        taiKhoanList.add(new TaiKhoan("Nguyễn Văn A", "0123456789", "user1", "pass1", "01/01/1990", "Hà Nội"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Văn B", "0987654321", "user2", "pass2", "02/02/1991", "TP.HCM"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Văn C", "0123456789", "user3", "pass3", "03/03/1992", "Đà Nẵng"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Văn D", "0987654321", "user4", "pass4", "04/04/1993", "Hải Phòng"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Văn E", "0123456789", "user5", "pass5", "05/05/1994", "Cần Thơ"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Thị F", "0123456789", "user6", "pass6", "06/06/1995", "Vĩnh Phúc"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Thị G", "0987654321", "user7", "pass7", "07/07/1996", "Hà Nam"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Thị H", "0123456789", "user8", "pass8", "08/08/1997", "Nam Định"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Thị I", "0987654321", "user9", "pass9", "09/09/1998", "Thái Bình"));
        taiKhoanList.add(new TaiKhoan("Nguyễn Thị K", "0123456789", "user10", "pass10", "10/10/1999", "Ninh Bình"));

        saveAllAccountsToFile();

        userPreferences = new UserPreferences(this);
        if (userPreferences.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        // Xử lý sự kiện click nút đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Kiểm tra thông tin đăng nhập
                int result = checkLogin(userName, password);

                switch (result) {
                    case 0: // NO_ACCOUNT
                        Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                        hoTenTextView.setText("");
                        soDienThoaiTextView.setText("");
                        break;
                    case 1: // WRONG_PASS
                        Toast.makeText(LoginActivity.this, "Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                        hoTenTextView.setText("");
                        soDienThoaiTextView.setText("");
                        break;
                    case 2: // SUCCESS
                        // Lấy thông tin tài khoản từ list
                        TaiKhoan taiKhoan = getTaiKhoan(userName, password);
                        if (taiKhoan != null) {
                            handleLoginSuccess(taiKhoan.getUserName(), taiKhoan.getHoTen(),
                                    taiKhoan.getDiaChi(), taiKhoan.getSoDienThoai(), taiKhoan.getNgaySinh());
                        }
                        break;
                }
            }
        });
    }

    private void init() {
        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        hoTenTextView = findViewById(R.id.hoTenTextView);
        soDienThoaiTextView = findViewById(R.id.soDienThoaiTextView);
    }

    // Hàm kiểm tra thông tin đăng nhập
    private int checkLogin(String userName, String password) {
        for (TaiKhoan taiKhoan : taiKhoanList) {
            if (taiKhoan.getUserName().equals(userName)) {
                if (taiKhoan.getPassword().equals(password)) {
                    return 2; // SUCCESS
                } else {
                    return 1; // WRONG_PASS
                }
            }
        }
        return 0; // NO_ACCOUNT
    }

    // Hàm lấy thông tin tài khoản từ list
    private TaiKhoan getTaiKhoan(String userName, String password) {
        for (TaiKhoan taiKhoan : taiKhoanList) {
            if (taiKhoan.getUserName().equals(userName) && taiKhoan.getPassword().equals(password)) {
                return taiKhoan;
            }
        }
        return null;
    }

    // Hàm lưu tất cả thông tin tài khoản vào file
    private void saveAllAccountsToFile() {
        StringBuilder data = new StringBuilder();
        for (TaiKhoan taiKhoan : taiKhoanList) {
            data.append(taiKhoan.getHoTen()).append("\n")
                    .append(taiKhoan.getSoDienThoai()).append("\n")
                    .append(taiKhoan.getUserName()).append("\n")
                    .append(taiKhoan.getPassword()).append("\n")
                    .append(taiKhoan.getNgaySinh()).append("\n")
                    .append(taiKhoan.getDiaChi()).append("\n\n");
        }

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(data.toString().getBytes());
            Toast.makeText(this, "All account data saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save account data", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLoginSuccess(String userName, String fullName, String address, String phoneNumber, String birthDate) {
        userPreferences.setIsLoggedIn(true);
        userPreferences.saveUserInfo(userName, fullName, address, phoneNumber, birthDate);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}