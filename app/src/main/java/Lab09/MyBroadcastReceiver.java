package Lab09;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            // Xử lý sự kiện thay đổi trạng thái pin
            Toast.makeText(context, "Battery status changed", Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            // Xử lý sự kiện thiết bị được kết nối với nguồn điện
            Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            // Xử lý sự kiện thiết bị bị ngắt kết nối với nguồn điện
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
        } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            // Xử lý sự kiện thay đổi trạng thái kết nối mạng
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                Toast.makeText(context, "Network connected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Network disconnected", Toast.LENGTH_SHORT).show();
            }
        } else if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
            // Xử lý sự kiện thay đổi chế độ máy bay
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            if (isAirplaneModeOn) {
                Toast.makeText(context, "Airplane mode enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Airplane mode disabled", Toast.LENGTH_SHORT).show();
            }
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            // Xử lý sự kiện thiết bị khởi động lại
            Toast.makeText(context, "Device rebooted", Toast.LENGTH_SHORT).show();
        }
//        else if ("ACTION_CUSTOM_BROADCAST".equals(action)) {
////            // Khởi động một Activity
////            Intent activityIntent = new Intent(context, AppReceiverActivity.class);
////            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            context.startActivity(activityIntent);
//
//            // Hoặc khởi động một Service
//             Intent serviceIntent = new Intent(context, MyService.class);
//             context.startService(serviceIntent);
//
//            Toast.makeText(context, "Received broadcast and started activity/service", Toast.LENGTH_SHORT).show();
//        }
    }
}

