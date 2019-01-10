package com.alhpa.narsinhaproducts.table;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ColorTable {

    @PrimaryKey @NonNull
    private String colorName;

    public ColorTable() {
    }

    @NonNull
    public String getColorName() {
        return colorName;
    }

    public void setColorName(@NonNull String colorName) {
        this.colorName = colorName;
    }

}
