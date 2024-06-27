package Lab06;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.TaiKhoan;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DanhBaActivity extends AppCompatActivity {

    private ListView listView;
    private List<TaiKhoan> taiKhoanList = new ArrayList<>();
    private List<String> displayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_ba);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listDanhBa);

        // Đọc dữ liệu từ file và hiển thị lên ListView
        readDataFromFile();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Lab07.AccountInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("taikhoan", taiKhoanList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void readDataFromFile() {
        try (FileInputStream fis = openFileInput("account_data.txt");
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                // Đọc từng tài khoản, mỗi tài khoản gồm 4 dòng
                String hoTen = line.trim();
                String soDienThoai = br.readLine().trim();
                String userName = br.readLine().trim();
                String password = br.readLine().trim();
                String ngaySinh = br.readLine().trim();
                String diaChi = br.readLine().trim();
                // Bỏ qua dòng trống giữa các tài khoản
                br.readLine();

                // Tạo đối tượng tài khoản và thêm vào danh sách
                TaiKhoan taiKhoan = new TaiKhoan(hoTen, soDienThoai, userName, password, ngaySinh, diaChi);
                taiKhoanList.add(taiKhoan);
            }
            displayTaiKhoanList();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to read data from file", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayTaiKhoanList() {
        // Tạo một danh sách các chuỗi để hiển thị trên ListView
        for (TaiKhoan taiKhoan : taiKhoanList) {
            String taiKhoanInfo = "Tên tài khoản: " + taiKhoan.getHoTen() + "\nSố điện thoại: " + taiKhoan.getSoDienThoai();
            displayList.add(taiKhoanInfo);
        }
        // Khởi tạo ArrayAdapter và gán nó cho ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);
    }
}