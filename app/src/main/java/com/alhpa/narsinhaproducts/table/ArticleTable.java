package com.alhpa.narsinhaproducts.table;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"articleName","colorName"})
public class ArticleTable {

    @NonNull
    private String articleName;
    @NonNull
    private String colorName;
    @NonNull
    private int quantityRemaining;

    public ArticleTable() {
    }

    @NonNull
    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(@NonNull String articleName) {
        this.articleName = articleName;
    }

    @NonNull
    public String getColorName() {
        return colorName;
    }

    public void setColorName(@NonNull String colorName) {
        this.colorName = colorName;
    }

    @NonNull
    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(@NonNull int quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }
}
