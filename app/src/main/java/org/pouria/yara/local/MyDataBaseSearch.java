package org.pouria.yara.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.pouria.yara.model.JSearch;

@Database(entities = JSearch.class , version = 1)
public abstract class MyDataBaseSearch extends RoomDatabase {
    public abstract SearchDao searchDao();
}
