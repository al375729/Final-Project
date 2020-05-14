package com.example.leagueoflgendsaccountinformation.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public abstract class ChampionsDao {

    @Insert
    public abstract void insertarCampeones(List<Champions> champions);

    /*
    @Query("SELECT * FROM leagues ORDER BY name")
    public abstract List<League> infoLigas();

    @Insert
    public abstract void insertarEquipos(List<Team> teams);*/

    @Query("SELECT * FROM champions WHERE id == (:id) ")
    public abstract Champions getChampionById(Integer id);

    @Query("SELECT * FROM champions WHERE name == (:name) ")
    public abstract Champions getChampionByString(String name);
}