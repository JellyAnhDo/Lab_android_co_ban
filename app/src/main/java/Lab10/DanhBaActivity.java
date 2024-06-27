package Lab10;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
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
import java.io.IOException;
import java.io.InputStream;
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
        AssetManager assetManager = getAssets();

        try {
            InputStream inputStream = assetManager.open("account_data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    TaiKhoan taiKhoan = new TaiKhoan(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim());
                    taiKhoanList.add(taiKhoan);
                }
            }
            displayTaiKhoanList();
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e("ProfileActivity", "Error reading contacts from file: " + e.getMessage());
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