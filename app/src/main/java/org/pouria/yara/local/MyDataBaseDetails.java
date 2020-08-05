package org.pouria.yara.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.pouria.yara.model.JDetails;
import org.pouria.yara.model.JSearch;

@Database(entities = JDetails.class , version = 1)
public abstract class MyDataBaseDetails extends RoomDatabase {
    public abstract DetailsDao detailsDao();

}
