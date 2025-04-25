package vn.iotstart.sql_lite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.iotstart.sql_lite.R;
import vn.iotstart.sql_lite.databinding.ItemListUserBinding;
import vn.iotstart.sql_lite.databinding.MyViewModel;
import vn.iotstart.sql_lite.databinding.User;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<MyViewModel> {
    private List<User> userList;

    public ListUserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListUserBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_list_user,
                parent,
                false);
        return new MyViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel holder, int position) {
        holder.bind(userList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    // Thêm interface này vào
    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}