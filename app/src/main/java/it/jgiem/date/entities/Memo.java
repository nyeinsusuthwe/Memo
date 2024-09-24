package it.jgiem.date.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Memo")
public class Memo implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String name;
    private String date;
    private String contact;
    private Date created;

    public Memo(){
    }

    @Ignore
    public Memo(int id, String name, String date, String contact){
        this.id = id;
        this.date = date;
        this.name = name;
        this.contact = contact;
        this.created = new Date();
    }

    @Ignore
    public Memo(String name, String date, String contact){
        this.name = name;
        this.date = date;
        this.contact = contact;
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
