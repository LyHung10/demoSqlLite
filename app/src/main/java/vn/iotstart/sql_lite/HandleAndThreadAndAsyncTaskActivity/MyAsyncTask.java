package vn.iotstart.sql_lite.HandleAndThreadAndAsyncTaskActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import vn.iotstart.sql_lite.R;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextParent;

    // Constructor
    public MyAsyncTask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Hàm này chạy đầu tiên trên UI thread
        Toast.makeText(contextParent, "Bắt đầu xử lý...", Toast.LENGTH_SHORT).show();

        // Reset progress bar
        ProgressBar progressBar = contextParent.findViewById(R.id.prbDemo);
        progressBar.setProgress(0);

        // Reset text view
        TextView textView = contextParent.findViewById(R.id.txtStatus);
        textView.setText("0%");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Hàm thực hiện công việc nặng trên background thread
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100); // Giả lập công việc tốn thời gian

            // Gửi tiến trình lên UI thread
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Cập nhật giao diện người dùng
        ProgressBar progressBar = contextParent.findViewById(R.id.prbDemo);
        TextView textView = contextParent.findViewById(R.id.txtStatus);

        int number = values[0];
        progressBar.setProgress(number);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Hàm này chạy khi tiến trình kết thúc
        Toast.makeText(contextParent, "Đã hoàn thành!", Toast.LENGTH_SHORT).show();
    }
}