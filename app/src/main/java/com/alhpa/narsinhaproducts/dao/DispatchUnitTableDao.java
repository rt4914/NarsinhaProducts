package com.alhpa.narsinhaproducts.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alhpa.narsinhaproducts.table.DispatchUnitTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DispatchUnitTableDao {

    @Query("SELECT * FROM dispatchUnitTable ORDER BY date ASC")
    List<DispatchUnitTable> getAllDispatchUnitNonLive();

    @Query("SELECT * FROM dispatchUnitTable WHERE challanNumber = :challanNumber")
    DispatchUnitTable getAllDispatchUnitByChallanNumberNonLive(String challanNumber);

    @Insert(onConflict = REPLACE)
    void insert(DispatchUnitTable dispatchUnitTable);

    @Update
    void update(DispatchUnitTable dispatchUnitTable);

    @Query("DELETE FROM dispatchUnitTable WHERE challanNumber = :challanNumber")
    void deleteByLotNumber(String challanNumber);

    @Query("DELETE FROM dispatchUnitTable")
    void deleteAll();

}
