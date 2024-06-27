package Lab09;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Code xử lý khi Service được khởi động
        Toast.makeText(this, "MyService started", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
}

