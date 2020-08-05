package org.pouria.yara.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class JRatings implements Serializable {
    @PrimaryKey (autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Source")
    public String Source;
    @ColumnInfo(name = "Value")
    public String Value;
}

