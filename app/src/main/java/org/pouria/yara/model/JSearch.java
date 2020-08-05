package org.pouria.yara.model;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class JSearch implements Serializable {
    @PrimaryKey (autoGenerate = true)
    public int id;
    @ColumnInfo(name = "imdbID")
    public String imdbID;
    @ColumnInfo(name = "Title")
    public String Title;
    @ColumnInfo(name = "Year")
    public String Year;
    @ColumnInfo(name = "Type")
    public String Type;
    @ColumnInfo(name = "Poster")
    public String Poster;


}
