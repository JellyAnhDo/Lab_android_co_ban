package Lab08;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.io.IOException;

public class MusicInforActivity extends AppCompatActivity {

    TextView tvTenBaiHat, tvTacGia, tvLink;
    Button btnPlay;
    MediaPlayer mediaPlayer;
    boolean isPlaying = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music_infor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTenBaiHat = findViewById(R.id.tvTenBaiHat);
        tvTacGia = findViewById(R.id.tvTacGia);
        btnPlay = findViewById(R.id.btnPlay);

        // Nhận thông tin bài hát từ intent
        BaiHat baiHat = (BaiHat) getIntent().getSerializableExtra("baihat");
        tvTenBaiHat.setText(baiHat.getTenBaiHat());
        tvTacGia.setText("Tác giả: " + baiHat.getTacGia());

        // Khởi tạo MediaPlayer
//        mediaPlayer = new MediaPlayer();
        // Lấy resource ID của bài hát từ file raw
        int resourceId = getApplicationContext().getResources().getIdentifier(baiHat.getLink(), "raw", getApplicationContext().getPackageName());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), resourceId);

        // Sự kiện khi ấn nút play
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Dừng bài hát
                    mediaPlayer.pause();
                    btnPlay.setText("Play");
                    isPlaying = false;
                } else {
                    // Bắt đầu phát bài hát
                    mediaPlayer.start();
                    btnPlay.setText("Pause");
                    isPlaying = true;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}