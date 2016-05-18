package com.grigorewski.ulafnow.content;

import com.google.gson.annotations.SerializedName;

/**
 * @author Igor Grigorewski
 */
public class Team {
    @SerializedName("Conference")
    private String conference;

    @SerializedName("Division")
    private String division;

    @SerializedName("Team")
    private String team;

    @SerializedName("Name")
    private String name;

    @SerializedName("Content")
    private String content;

    @SerializedName("Wins")
    private int wins;

    @SerializedName("Losses")
    private int losses;

    @SerializedName("Ties")
    private int ties;

    @SerializedName("Percentage")
    private float percentage;

    public Team( ){

    }

    public Team(String conference, String division, String team, String name, String content, int wins, int losses, int ties, float percentage) {
        this.conference = conference;
        this.division = division;
        this.team = team;
        this.name = name;
        this.content = content;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.percentage = percentage;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getConference() {
        return conference;
    }

    public String getDivision() {
        return division;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public float getPercentage() {
        return percentage;
    }
}
