package Lab10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    private UserPreferences userPreferences;
    private TextView tvFullName, tvAddress, tvPhoneNumber, tvBirthDate;
    private ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Init
        init();
        userPreferences = new UserPreferences(this);

        if(userPreferences.isLoggedIn()) {
            tvFullName.setText(userPreferences.getFullName());
            tvAddress.setText(userPreferences.getAddress());
            tvPhoneNumber.setText(userPreferences.getPhoneNumber());
            tvBirthDate.setText(userPreferences.getBirthDate());

            try {
                InputStream inputStream = getAssets().open("meo.jpg");
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                ivProfileImage.setImageDrawable(drawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void init() {
        tvFullName = findViewById(R.id.tvFullName);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        ivProfileImage = findViewById(R.id.ivProfileImage);
    }

}