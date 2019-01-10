package com.alhpa.narsinhaproducts.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alhpa.narsinhaproducts.table.ColorTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ColorTableDao {

    @Query("SELECT * FROM colorTable ORDER BY colorName ASC")
    List<ColorTable> getAllColorNonLive();

    @Insert(onConflict = REPLACE)
    void insert(ColorTable colorTable);

    @Query("DELETE FROM colorTable WHERE colorName = :sName")
    void deleteByName(String sName);

    @Query("DELETE FROM colorTable")
    void deleteAll();

}
