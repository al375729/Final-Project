package com.example.leagueoflgendsaccountinformation.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities ={ Champions.class}, version =1)
public abstract class ChampionsDatabase extends RoomDatabase {

    public abstract ChampionsDao getChampionsDao();
}
