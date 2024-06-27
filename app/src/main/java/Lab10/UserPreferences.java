package Lab10;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    private static final String PREFS_NAME = "user_prefs";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_BIRTH_DATE = "birth_date";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void saveUserInfo(String userName, String fullName, String address, String phoneNumber, String birthDate) {
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_FULL_NAME, fullName);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.putString(KEY_BIRTH_DATE, birthDate);
        editor.apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, "");
    }

    public String getFullName() {
        return sharedPreferences.getString(KEY_FULL_NAME, "");
    }

    public String getAddress() {
        return sharedPreferences.getString(KEY_ADDRESS, "");
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString(KEY_PHONE_NUMBER, "");
    }

    public String getBirthDate() {
        return sharedPreferences.getString(KEY_BIRTH_DATE, "");
    }
}
