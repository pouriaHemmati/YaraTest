package org.pouria.yara.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.pouria.yara.model.JDetails;
import org.pouria.yara.model.JRatings;
import org.pouria.yara.model.JSearch;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DetailsDao {
    @Query("SELECT * FROM JDetails WHERE imdbID LIKE :imdbID LIMIT 1")
    JDetails getAllDetails(String imdbID);

    @Insert
    void setAllDetails(JDetails jDetails );


    @Delete
    void delete(List<JDetails> jDetails);

    @Query("SELECT * FROM JDetails WHERE imdbID LIKE :imdbID LIMIT 1" )
    List<JDetails> findByName(String imdbID);

    @Query("SELECT * FROM JDetails")
    List<JDetails> getAll();

}
