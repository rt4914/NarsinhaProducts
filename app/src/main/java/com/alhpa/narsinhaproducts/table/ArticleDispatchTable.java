package com.alhpa.narsinhaproducts.table;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"challanNumber","articleDispatchName","colorName"})
public class ArticleDispatchTable {

    @NonNull
    private String challanNumber;
    @NonNull
    private String articleDispatchName;
    @NonNull
    private String colorName;
    @NonNull
    private int quantityDispatched;

    public ArticleDispatchTable() {
    }

    @NonNull
    public String getChallanNumber() {
        return challanNumber;
    }

    public void setChallanNumber(@NonNull String challanNumber) {
        this.challanNumber = challanNumber;
    }

    @NonNull
    public String getArticleDispatchName() {
        return articleDispatchName;
    }

    public void setArticleDispatchName(@NonNull String articleDispatchName) {
        this.articleDispatchName = articleDispatchName;
    }

    @NonNull
    public String getColorName() {
        return colorName;
    }

    public void setColorName(@NonNull String colorName) {
        this.colorName = colorName;
    }

    @NonNull
    public int getQuantityDispatched() {
        return quantityDispatched;
    }

    public void setQuantityDispatched(@NonNull int quantityDispatched) {
        this.quantityDispatched = quantityDispatched;
    }
}
