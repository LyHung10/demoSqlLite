package vn.iotstart.sql_lite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import vn.iotstart.sql_lite.MainActivity;
import vn.iotstart.sql_lite.R;
import vn.iotstart.sql_lite.model.NotesModel;

public class NoteAdapter extends BaseAdapter {
    // Khai báo biến toàn cục
    private Context context;
    private int layout;
    private List<NotesModel> noteList;

    // Constructor
    public NoteAdapter(Context context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }
    // Tạo class ViewHolder để tối ưu hiệu suất
    private class ViewHolder {
        TextView textViewNote;
        ImageView imageViewEdit, imageViewDelete, imageViewAdd;
    }
    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gọi ViewHolder
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);

            // Ánh xạ các thành phần UI
            viewHolder.textViewNote = convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewAdd = convertView.findViewById(R.id.imageViewAdd);
            viewHolder.imageViewDelete = convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = convertView.findViewById(R.id.imageViewEdit);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy giá trị từ danh sách , sai chung cho CRUD
        NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());

        // Bắt sự kiện click vào imageViewAdd để mở Dialog thêm ghi chú
        viewHolder.imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).DialogThem(); // Gọi phương thức DialogThem() từ MainActivity
                }
            }
        });

        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    // dialog trong main activity.java
                    ((MainActivity) context).DialogUpdateNotes(notes.getNameNote(), notes.getIdNote());
                }
            }
        });

        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    // dialog trong main activity.java
                    ((MainActivity) context).DialogDeleteNodes(notes.getNameNote(), notes.getIdNote());
                }
            }
        });

        return convertView;
    }

}
