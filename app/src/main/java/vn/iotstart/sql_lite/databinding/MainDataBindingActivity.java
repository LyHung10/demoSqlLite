package vn.iotstart.sql_lite.databinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import vn.iotstart.sql_lite.R;
import vn.iotstart.sql_lite.model.User;

public class MainDataBindingActivity extends AppCompatActivity {

    private  User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gắn layout bằng DataBindingUtil
        ActivityMainDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main_data_binding);

        // Tạo user model
        user = new User("Nguyen", "Van A");

        // Gán dữ liệu cho biến user trong XML
        binding.setUser(user);
    }
}