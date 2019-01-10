package com.alhpa.narsinhaproducts.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alhpa.narsinhaproducts.table.ArticleDispatchTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleDispatchTableDao {

    @Query("SELECT * FROM articleDispatchTable ORDER BY challanNumber ASC")
    List<ArticleDispatchTable> getAllArticleDispatchNonLive();

    @Query("SELECT * FROM articleDispatchTable WHERE challanNumber = :sName")
    List<ArticleDispatchTable> getAllArticleDispatchByChallanNumberNonLive(String sName);

    @Query("SELECT * FROM articleDispatchTable WHERE articleDispatchName = :sName AND colorName = :sColor")
    List<ArticleDispatchTable> getAllArticleDispatchByArticleDispatchNameAndColorNonLive(String sName, String sColor);

    @Insert(onConflict = REPLACE)
    void insert(ArticleDispatchTable articleDispatchTable);

    @Query("DELETE FROM articleDispatchTable WHERE challanNumber = :sName")
    void deleteByChallanNumber(String sName);

    @Query("DELETE FROM articleDispatchTable WHERE articleDispatchName = :sName AND colorName = :sColor")
    void deleteByNameAndColor(String sName, String sColor);

    @Query("DELETE FROM articleDispatchTable")
    void deleteAll();

}
