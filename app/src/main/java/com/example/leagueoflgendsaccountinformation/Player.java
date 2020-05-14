package com.example.leagueoflgendsaccountinformation;

import java.io.Serializable;

public class Player implements Serializable {

    public String nombre;
    public String id;
    public String accountId;
    public int iconId;
    public int level;

    public Player(String nombre, String id,String accountId ,int iconId, int level){
        this.nombre=nombre;
        this.id=id;
        this.accountId=accountId;
        this.iconId=iconId;
        this.level=level;
    }

}
