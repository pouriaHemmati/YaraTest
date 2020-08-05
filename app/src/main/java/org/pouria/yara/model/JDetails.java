package org.pouria.yara.model;

import android.media.Rating;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class JDetails implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Title")
    public String Title;
    @ColumnInfo(name = "Year")
    public String Year;
    @ColumnInfo(name = "Rated")
    public String Rated;
    @ColumnInfo(name = "Released")
    public String Released;
    @ColumnInfo(name = "Runtime")
    public String Runtime;
    @ColumnInfo(name = "Genre")
    public String Genre;
    @ColumnInfo(name = "Director")
    public String Director;
    @ColumnInfo(name = "Writer")
    public String Writer;
    @ColumnInfo(name = "Actors")
    public String Actors;
    @ColumnInfo(name = "Plot")
    public String Plot;
    @ColumnInfo(name = "Language")
    public String Language;
    @ColumnInfo(name = "Country")
    public String Country;
    @ColumnInfo(name = "Awards")
    public String Awards;
    @ColumnInfo(name = "Poster")
    public String Poster;
    @ColumnInfo(name = "Metascore")
    public String Metascore;
    @ColumnInfo(name = "imdbRating")
    public String imdbRating;
    @ColumnInfo(name = "imdbVotes")
    public String imdbVotes;
    @ColumnInfo(name = "imdbID")
    public String imdbID;
    @ColumnInfo(name = "Type")
    public String Type;
    @ColumnInfo(name = "DVD")
    public String DVD;
    @ColumnInfo(name = "BoxOffice")
    public String BoxOffice;
    @ColumnInfo(name = "Production")
    public String Production;
    @Embedded
    @Expose
    @SerializedName("ratings")
    public ArrayList<JRatings> Ratings;



}
