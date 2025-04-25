package vn.iotstart.sql_lite.databinding;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import vn.iotstart.sql_lite.adapter.ListUserAdapter;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;
import vn.iotstart.sql_lite.databinding.ItemListUserBinding;

public class MyViewModel extends RecyclerView.ViewHolder {
    public final ObservableField<String> stt = new ObservableField<>();
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();

    private final ItemListUserBinding binding;

    public MyViewModel(ItemListUserBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.setViewModel(this); // Phải khớp với tên trong XML
        this.binding.executePendingBindings();
    }

    public void bind(User user, int position) {
        stt.set(String.valueOf(position + 1));
        firstName.set(user.getFirstName());
        lastName.set(user.getLastName());
    }
}