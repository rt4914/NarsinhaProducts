package com.alhpa.narsinhaproducts.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alhpa.narsinhaproducts.table.ArticleNameTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleNameTableDao {

    @Query("SELECT articleName FROM articleNameTable ORDER BY articleName ASC")
    List<String> getAllArticleNonLive();

    @Insert(onConflict = REPLACE)
    void insert(ArticleNameTable articleNameTable);

    @Query("DELETE FROM articleNameTable WHERE articleName = :sName")
    void deleteByName(String sName);

    @Query("DELETE FROM articleNameTable")
    void deleteAll();

}
