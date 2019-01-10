package com.alhpa.narsinhaproducts.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alhpa.narsinhaproducts.table.MainUnitTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainUnitTableDao {

    @Query("SELECT * FROM mainUnitTable ORDER BY lotNumber ASC")
    List<MainUnitTable> getAllMainUnitNonLive();

    @Query("SELECT * FROM mainUnitTable WHERE endDate==0")
    List<MainUnitTable> getAllMainUnitWhereEndDateIsZero();

    @Query("SELECT * FROM mainUnitTable ORDER BY endDate ASC")
    List<MainUnitTable> getAllMainUnitByEndDateNonLive();

    @Query("SELECT * FROM mainUnitTable ORDER BY startDate ASC")
    List<MainUnitTable> getAllMainUnitByStartDateNonLive();

    @Query("SELECT * FROM mainUnitTable WHERE lotNumber = :sLotNumber")
    MainUnitTable getAllMainUnitByMainUnitNameNonLive(String sLotNumber);

    @Insert(onConflict = REPLACE)
    void insert(MainUnitTable mainUnitTable);

    @Update
    void update(MainUnitTable mainUnitTable);

    @Query("DELETE FROM mainUnitTable WHERE lotNumber = :sLotNumber")
    void deleteByLotNumber(String sLotNumber);

    @Query("DELETE FROM mainUnitTable")
    void deleteAll();

}
