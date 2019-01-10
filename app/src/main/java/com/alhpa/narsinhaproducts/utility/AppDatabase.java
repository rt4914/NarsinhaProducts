package com.alhpa.narsinhaproducts.utility;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.alhpa.narsinhaproducts.dao.ArticleDispatchTableDao;
import com.alhpa.narsinhaproducts.dao.ArticleNameTableDao;
import com.alhpa.narsinhaproducts.dao.ArticleTableDao;
import com.alhpa.narsinhaproducts.dao.ColorTableDao;
import com.alhpa.narsinhaproducts.dao.DispatchUnitTableDao;
import com.alhpa.narsinhaproducts.dao.MainUnitTableDao;
import com.alhpa.narsinhaproducts.dao.ProductionTableDao;
import com.alhpa.narsinhaproducts.table.ArticleDispatchTable;
import com.alhpa.narsinhaproducts.table.ArticleNameTable;
import com.alhpa.narsinhaproducts.table.ArticleTable;
import com.alhpa.narsinhaproducts.table.ColorTable;
import com.alhpa.narsinhaproducts.table.DispatchUnitTable;
import com.alhpa.narsinhaproducts.table.MainUnitTable;
import com.alhpa.narsinhaproducts.table.ProductionTable;

@Database(entities = {
        ArticleDispatchTable.class,
        ArticleNameTable.class,
        ArticleTable.class,
        ColorTable.class,
        DispatchUnitTable.class,
        MainUnitTable.class,
        ProductionTable.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();

    private static AppDatabase appDatabase;

    public abstract ArticleDispatchTableDao articleDispatchTableDao();

    public abstract ArticleNameTableDao articleNameTableDao();

    public abstract ArticleTableDao articleTableDao();

    public abstract ColorTableDao colorTableDao();

    public abstract DispatchUnitTableDao dispatchUnitTableDao();

    public abstract MainUnitTableDao mainUnitTableDao();

    public abstract ProductionTableDao productionTableDao();

    private Context context;

    public static AppDatabase getInstance(Context context) {

        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "SLIPPERS_DATABASE")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;

    }

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//
//            Log.d(TAG, "migrate: ");
//
//        }
//    };
//
//    public static void destroyInstance() {
//        appDatabase = null;
//    }

}