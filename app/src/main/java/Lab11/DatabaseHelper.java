package Lab11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DB_TAIKHOAN";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "taikhoans";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONENUMBER = "phone_number";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_ADDRESS = "address";


    public static final String CREATE_TABLE ="" +
            "CREATE TABLE "+TABLE_NAME+" ("
            +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +KEY_NAME +" TEXT, "
            +KEY_PHONENUMBER +" TEXT, "
            +KEY_USERNAME +" TEXT, "
            +KEY_PASSWORD +" TEXT,"
            +KEY_BIRTHDAY +" TEXT, "
            +KEY_ADDRESS +" TEXT)";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,taiKhoan.getHoTen());
        contentValues.put(KEY_PHONENUMBER,taiKhoan.getSoDienThoai());
        contentValues.put(KEY_USERNAME, taiKhoan.getUserName());
        contentValues.put(KEY_PASSWORD,taiKhoan.getPassword());
        contentValues.put(KEY_BIRTHDAY,taiKhoan.getNgaySinh());
        contentValues.put(KEY_ADDRESS,taiKhoan.getDiaChi());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public void deleteTaiKhoan(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.KEY_ID + " = " + id, null);
    }

    public List<TaiKhoan> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setId(cursor.getInt(0));
            taiKhoan.setHoTen(cursor.getString(1));
            taiKhoan.setSoDienThoai(cursor.getString(2));
            taiKhoan.setUserName(cursor.getString(3));
            taiKhoan.setPassword(cursor.getString(4));
            taiKhoan.setNgaySinh(cursor.getString(5));
            taiKhoan.setDiaChi(cursor.getString(6));

            list.add(taiKhoan);
            cursor.moveToNext();
        }

        return list;
    }
}
