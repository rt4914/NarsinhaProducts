package com.alhpa.narsinhaproducts.table;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ArticleNameTable {

    @NonNull @PrimaryKey
    private String articleName;

    public ArticleNameTable() {
    }

    @NonNull
    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(@NonNull String articleName) {
        this.articleName = articleName;
    }

}
