package vn.iotstart.sql_lite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Tên database & bảng
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Users";

    // Cột trong bảng Users
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    // Constructor
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng khi database được tạo lần đầu
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);
    }

    // Nâng cấp database (khi thay đổi version)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // **Thực thi câu lệnh không trả kết quả (INSERT, UPDATE, DELETE, CREATE)**
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // **Thực thi câu lệnh có trả kết quả (SELECT)**
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    // **Thêm người dùng mới**
    public void addUser(String email, String password) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ") VALUES ('" + email + "', '" + password + "')";
        QueryData(sql);
    }

    // **Kiểm tra người dùng có tồn tại không**
    public boolean checkUser(String email, String password) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = '" + email + "' AND " + COLUMN_PASSWORD + " = '" + password + "'";
        Cursor cursor = GetData(sql);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
