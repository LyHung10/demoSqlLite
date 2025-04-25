package vn.iotstart.sql_lite.volleyActivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.iotstart.sql_lite.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView id, userName, userEmail, gender;
    Button btnLogout;
    ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        id = findViewById(R.id.textViewId);
        userName = findViewById(R.id.textViewUsername);
        userEmail = findViewById(R.id.textViewEmail);
        gender = findViewById(R.id.textViewGender);
        btnLogout = findViewById(R.id.buttonLogout);
        imageViewProfile = findViewById(R.id.imageViewProfile);

        User user = SharedPrefManager.getInstance(this).getUser();
        id.setText("ID: " + String.valueOf(user.getId()));
        userName.setText("Username: " + user.getName());
        userEmail.setText("Email: " + user.getEmail());
        gender.setText("Gender: " + user.getGender());

        Glide.with(getApplicationContext())
                .load(user.getImages())
                .into(imageViewProfile);

        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnLogout)) {
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}