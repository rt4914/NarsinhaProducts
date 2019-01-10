package com.alhpa.narsinhaproducts.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alhpa.narsinhaproducts.table.ArticleTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleTableDao {

    @Query("SELECT * FROM articleTable ORDER BY articleName ASC")
    List<ArticleTable> getAllArticleNonLive();

    @Query("SELECT * FROM articleTable WHERE articleName = :sName")
    List<ArticleTable> getAllArticleByArticleNameNonLive(String sName);

    @Query("SELECT * FROM articleTable WHERE articleName = :sName AND colorName = :sColor")
    ArticleTable getAllArticleByArticleNameAndColorNonLive(String sName, String sColor);

    @Insert(onConflict = REPLACE)
    void insert(ArticleTable articleTable);

    @Query("DELETE FROM articleTable WHERE articleName = :sName")
    void deleteByName(String sName);

    @Query("DELETE FROM articleTable WHERE articleName = :sName AND colorName = :sColor")
    void deleteByNameAndColor(String sName, String sColor);

    @Query("DELETE FROM articleTable")
    void deleteAll();

}
