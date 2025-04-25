package vn.iotstart.sql_lite.HandleAndThreadAndAsyncTaskActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.iotstart.sql_lite.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainAsyncctaskActivity extends AppCompatActivity {
    Button btnStart;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_asyncctask);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo và thực thi AsyncTask
                myAsyncTask = new MyAsyncTask(MainAsyncctaskActivity.this);
                myAsyncTask.execute();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy AsyncTask khi Activity bị hủy để tránh memory leak
        if (myAsyncTask != null && myAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            myAsyncTask.cancel(true);
        }
    }
}