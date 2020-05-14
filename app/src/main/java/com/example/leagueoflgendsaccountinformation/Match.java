package com.example.leagueoflgendsaccountinformation;

public class Match {

    public int kills;
    public int deaths;
    public int assists;
    public String win;
    public int championId;
    public String championName;
    public int J1Campeon;
    public int J2Campeon;
    public int J3Campeon;
    public int J4Campeon;
    public int J5Campeon;
    public int J6Campeon;
    public int J7Campeon;
    public int J8Campeon;
    public int J9Campeon;
    public int J10Campeon;
    public int team1Towers;
    public int team2Towers;
    public int team1Inhibitors;
    public int team2Inhibitors;
    public int team1Baron;
    public int team2Baron;
    public int team1Herald;
    public int team2Herald;
    public int team1Dragon;
    public int team2Dragon;

    public Match(int kills, int deaths, int assists, String win,int championId,String championName, int j1Campeon, int j2Campeon, int j3Campeon, int j4Campeon, int j5Campeon,
                 int j6Campeon, int j7Campeon, int j8Campeon, int j9Campeon, int j10Campeon,int team1Towers,int team1Inhibitors,int team1Baron,int team1Herald,int team1Dragon
    ,int team2Towers,int team2Inhibitors,int team2Baron,int team2Herald,int team2Dragon) {
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.win = win;
        this.championId = championId;
        this.championName = championName;
        J1Campeon = j1Campeon;
        J2Campeon = j2Campeon;
        J3Campeon = j3Campeon;
        J4Campeon = j4Campeon;
        J5Campeon = j5Campeon;
        J6Campeon = j6Campeon;
        J7Campeon = j7Campeon;
        J8Campeon = j8Campeon;
        J9Campeon = j9Campeon;
        J10Campeon = j10Campeon;
        this.team1Towers = team1Towers;
        this.team1Inhibitors = team1Inhibitors;
        this.team1Baron = team1Baron;
        this.team1Herald = team1Herald;
        this.team1Dragon = team1Dragon;
        this.team2Towers = team2Towers;
        this.team2Inhibitors = team2Inhibitors;
        this.team2Baron = team2Baron;
        this.team2Herald = team2Herald;
        this.team2Dragon = team2Dragon;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }
}
