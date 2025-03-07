package vn.iotstart.sql_lite.model;

import java.io.Serializable;

public class NotesModel implements Serializable {
    private int IdNote;
    private String NameNote;

    // Constructor mặc định (cần thiết cho một số trường hợp như Firebase hoặc JSON parsing)
    public NotesModel() {}

    // Constructor đầy đủ tham số
    public NotesModel(int idNote, String nameNote) {
        this.IdNote = idNote;
        this.NameNote = nameNote;
    }

    // Getter & Setter cho IdNote
    public int getIdNote() {
        return IdNote;
    }

    public void setIdNote(int idNote) {
        this.IdNote = idNote;
    }

    // Getter & Setter cho NameNote
    public String getNameNote() {
        return NameNote;
    }

    public void setNameNote(String nameNote) {
        this.NameNote = nameNote;
    }
}
