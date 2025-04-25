package vn.iotstart.sql_lite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import vn.iotstart.sql_lite.adapter.NoteAdapter;
import vn.iotstart.sql_lite.model.NotesModel;

public class MainSQLliteActivity extends AppCompatActivity {
    // Khai báo biến toàn cục
    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gọi hàm khởi tạo database và thao tác với SQLite
        InitDatabaseSQLite();
        createDatabaseSQLite();

        // Khởi tạo ListView và Adapter trước khi load dữ liệu
        initListView();

        // Load dữ liệu từ SQLite sau khi adapter đã sẵn sàng
        databaseSQLite();

    }

    // Khởi tạo database và bảng Notes
    private void InitDatabaseSQLite() {
        databaseHandler = new DatabaseHandler(this);
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    // Thêm dữ liệu vào bảng Notes
    private void createDatabaseSQLite() {
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Ví dụ SQLite 1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Ví dụ SQLite 2')");
    }

    // Ánh xạ ListView và khởi tạo Adapter
    private void initListView() {
        listView = findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NoteAdapter(this, R.layout.row_image, arrayList);
        listView.setAdapter(adapter);
    }

    // Lấy dữ liệu từ bảng Notes và cập nhật Adapter
    private void databaseSQLite() {
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");

        // Xóa danh sách cũ để tránh trùng lặp
        arrayList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            arrayList.add(new NotesModel(id, name));
        }

        cursor.close(); // Đóng cursor sau khi lấy dữ liệu
        adapter.notifyDataSetChanged(); // Cập nhật ListView
    }
    // goi menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // bat sư kien cho menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAddNotes) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    public void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_notes);

        EditText addText = dialog.findViewById(R.id.editTextName);
        Button buttonAdd = dialog.findViewById(R.id.buttonEdit);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = addText.getText().toString().trim();
                if (noteText.isEmpty()) {
                    Toast.makeText(MainSQLliteActivity.this, "Vui lòng nhập tên Notes", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseHandler.QueryData("INSERT INTO Notes VALUES(null, '" + noteText + "')");
                Toast.makeText(MainSQLliteActivity.this, "Đã thêm Notes", Toast.LENGTH_SHORT).show();
                databaseSQLite(); // Gọi hàm load lại dữ liệu
                dialog.dismiss();
            }
        });

        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogUpdateNotes(String name, int id) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_notes);

        // Ánh xạ view
        EditText editText = dialog.findViewById(R.id.editTextName);
        Button buttonEdit = dialog.findViewById(R.id.buttonEdit);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuy);

        // Hiển thị tên ghi chú cũ
        editText.setText(name);

        // Bắt sự kiện cập nhật
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editText.getText().toString().trim();
                if (!newName.isEmpty()) {
                    // Cập nhật dữ liệu SQLite
                    databaseHandler.QueryData("UPDATE Notes SET NameNotes = '" + newName + "' WHERE Id = " + id);
                    Toast.makeText(MainSQLliteActivity.this, "Đã cập nhật Notes thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    databaseSQLite(); // Load lại dữ liệu sau khi cập nhật
                } else {
                    Toast.makeText(MainSQLliteActivity.this, "Tên ghi chú không được để trống!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Bắt sự kiện hủy
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogDeleteNodes (String name, final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa notes" + name +"này không");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
                public void onClick (DialogInterface dialog,int which) {
                databaseHandler.QueryData("DELETE FROM Notes WHERE Id = '" + id + "'");
                Toast.makeText(MainSQLliteActivity.this, "Đã xóa notes" + name + "thành công", Toast.LENGTH_SHORT).show();
                databaseSQLite();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });
        builder.show();
    }
}
