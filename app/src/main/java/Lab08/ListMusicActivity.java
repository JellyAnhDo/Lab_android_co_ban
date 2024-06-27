package Lab08;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.TaiKhoan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListMusicActivity extends AppCompatActivity {
    private ListView listBaiHat;
    private List<BaiHat> danhSachBaiHat = new ArrayList<>();
    private List<String> displayList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_music);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listBaiHat = findViewById(R.id.listBaiHat);

        // Khởi tạo danh sách bài hát
        danhSachBaiHat = new ArrayList<>();
        danhSachBaiHat.add(new BaiHat("Bài hát 1", "Tác giả 1", "aloi"));
        danhSachBaiHat.add(new BaiHat("Bài hát 2", "Tác giả 2", "gio"));
        // ... thêm bài hát khác

        // Tạo một danh sách các chuỗi để hiển thị trên ListView
        for (BaiHat baiHat : danhSachBaiHat) {
            String baiHatInfo = "Tên bài hát: " + baiHat.getTenBaiHat() + "\nTác giả: " + baiHat.getTacGia();
            displayList.add(baiHatInfo);
        }
        // Tạo adapter hiển thị danh sách bài hát
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        listBaiHat.setAdapter(adapter);

        listBaiHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiHat baiHat = danhSachBaiHat.get(position);

                // Truyền thông tin bài hát sang activity thông tin bài hát
                Intent intent = new Intent(getApplicationContext(), MusicInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("baihat", baiHat);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}