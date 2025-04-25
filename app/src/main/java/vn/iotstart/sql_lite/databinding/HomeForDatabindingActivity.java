package vn.iotstart.sql_lite.databinding;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import vn.iotstart.sql_lite.R;
import vn.iotstart.sql_lite.adapter.ListUserAdapter;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class HomeForDatabindingActivity extends AppCompatActivity implements ListUserAdapter.OnItemClickListener {
    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;
    private ActivityHomeForDatabindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_for_databinding);
        title.set("Ví dụ về DataBinding cho RecyclerView");
        binding.setHome(this);

        // Phải khởi tạo adapter trước khi set cho RecyclerView
        setData();

        binding.reView.setLayoutManager(new LinearLayoutManager(this));
        binding.reView.setAdapter(listUserAdapter);
        listUserAdapter.setOnItemClickListener(this);
    }

    private void setData() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName("Họ " + i);
            user.setLastName("Trung" + i);
            userList.add(user);
        }
        listUserAdapter = new ListUserAdapter(userList);
    }

    @Override
    public void onItemClick(User user) {
        Toast.makeText(this, "Clicked: " + user.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}