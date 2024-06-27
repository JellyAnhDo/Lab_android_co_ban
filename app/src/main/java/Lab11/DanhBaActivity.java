package Lab11;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.TaiKhoan;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DanhBaActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnAdd, btnDelete;
    private ArrayAdapter<String> adapter;
    private List<TaiKhoan> taiKhoanList = new ArrayList<>();
    private List<String> displayList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private int selectedTaiKhoanId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_ba_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();
//


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTaiKhoanId = taiKhoanList.get(position).getId();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContactDialog();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTaiKhoan();
            }
        });

        // Đọc dữ liệu từ file và hiển thị lên ListView
        updateTaiKhoanList();
    }

    private void init() {
        listView = findViewById(R.id.listDanhBa);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void updateTaiKhoanList() {
        taiKhoanList.clear();
        displayList.clear();
        List<TaiKhoan> taikhoans = databaseHelper.getAll();
        for (TaiKhoan taiKhoan : taikhoans) {
            taiKhoanList.add(taiKhoan);
        }
        displayTaiKhoanList();
    }

    private void displayTaiKhoanList() {
        // Tạo một danh sách các chuỗi để hiển thị trên ListView
        for (TaiKhoan taiKhoan : taiKhoanList) {
            String taiKhoanInfo = "Tên tài khoản: " + taiKhoan.getHoTen() + "\nSố điện thoại: " + taiKhoan.getSoDienThoai();
            displayList.add(taiKhoanInfo);
        }
        // Khởi tạo ArrayAdapter và gán nó cho ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);
    }


    private void showAddContactDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_tai_khoan, null);
        dialogBuilder.setView(dialogView);

        final EditText etName = dialogView.findViewById(R.id.textName);
        final EditText etPhone = dialogView.findViewById(R.id.textSDT);
        final EditText etUsername = dialogView.findViewById(R.id.textUsername);
        final EditText etPassword = dialogView.findViewById(R.id.textPassword);
        final EditText etNgaySinh = dialogView.findViewById(R.id.textNgaySinh);
        final EditText etDiaChi = dialogView.findViewById(R.id.textDiaChi);

        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String birthday = etNgaySinh.getText().toString();
                String address = etDiaChi.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty() && !username.isEmpty() && !password.isEmpty() && !birthday.isEmpty() && !address.isEmpty()) {
                    TaiKhoan taikhoan = new TaiKhoan(name, phone, username, password, birthday, address);
                    databaseHelper.addTaiKhoan(taikhoan);
                    updateTaiKhoanList();
                    Toast.makeText(DanhBaActivity.this, "Contact added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DanhBaActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void deleteTaiKhoan() {
        if (selectedTaiKhoanId != -1) {
            databaseHelper.deleteTaiKhoan(selectedTaiKhoanId);
            updateTaiKhoanList();
            Toast.makeText(this, "Tài khoản đã được xóa", Toast.LENGTH_SHORT).show();
            selectedTaiKhoanId = -1;
        } else {
            Toast.makeText(this, "Vui lòng chọn một tài khoản để xóa!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}