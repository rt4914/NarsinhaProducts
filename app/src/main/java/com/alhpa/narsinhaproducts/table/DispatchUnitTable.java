package com.alhpa.narsinhaproducts.table;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"challanNumber"})
public class DispatchUnitTable {

     @NonNull
    private String challanNumber;
    private String partyName;
    private long date;

    public DispatchUnitTable() {
    }

    @NonNull
    public String getChallanNumber() {
        return challanNumber;
    }

    public void setChallanNumber(@NonNull String challanNumber) {
        this.challanNumber = challanNumber;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
