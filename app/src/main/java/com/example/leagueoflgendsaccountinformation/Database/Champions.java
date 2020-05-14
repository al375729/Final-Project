package com.example.leagueoflgendsaccountinformation.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "champions")
public class Champions implements Serializable {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "blurb")
    public String blurb;


    public Champions(int id, String name, String title, String blurb) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.blurb = blurb;
    }
}
