package org.pouria.yara.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.pouria.yara.model.JDetails;
import org.pouria.yara.model.JSearch;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SearchDao {
    @Query("SELECT * FROM JSearch")
    List<JSearch> getAll();

    @Insert
    void searchAll(ArrayList<JSearch> jSearchList);

    @Delete
    void delete(ArrayList<JSearch> jSearchList);

}
