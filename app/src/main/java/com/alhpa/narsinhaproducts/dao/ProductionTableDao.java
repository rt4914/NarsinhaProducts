package com.alhpa.narsinhaproducts.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alhpa.narsinhaproducts.table.ProductionTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductionTableDao {

    @Query("SELECT * FROM productionTable ORDER BY lotNumber ASC")
    List<ProductionTable> getAllProductionNonLive();

    @Query("SELECT * FROM productionTable WHERE endDate==0")
    List<ProductionTable> getAllProductionWhereEndDateIsZero();

    @Query("SELECT * FROM productionTable ORDER BY endDate ASC")
    List<ProductionTable> getAllProductionByEndDateNonLive();

    @Query("SELECT * FROM productionTable ORDER BY startDate ASC")
    List<ProductionTable> getAllProductionByStartDateNonLive();

    @Query("SELECT * FROM productionTable WHERE lotNumber = :sLotNumber")
    ProductionTable getAllProductionByProductionNameNonLive(String sLotNumber);

    @Insert(onConflict = REPLACE)
    void insert(ProductionTable productionTable);

    @Update
    void update(ProductionTable productionTable);

    @Query("DELETE FROM productionTable WHERE lotNumber = :sLotNumber")
    void deleteByLotNumber(String sLotNumber);

    @Query("DELETE FROM productionTable")
    void deleteAll();

}
