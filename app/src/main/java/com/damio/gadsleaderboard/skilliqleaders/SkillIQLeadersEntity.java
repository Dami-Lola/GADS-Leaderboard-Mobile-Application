package com.damio.gadsleaderboard.skilliqleaders;

import com.google.gson.annotations.SerializedName;

public class SkillIQLeadersEntity {

    @SerializedName("name")
    private String name;

    @SerializedName("score")
    private String score;

    @SerializedName("country")
    private String country;

    @SerializedName("badgeUrl")
    private String badgeUrl;

    public SkillIQLeadersEntity() {
    }

    public SkillIQLeadersEntity(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public String toString() {
        return "SkillIQLeadersEntity{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", country='" + country + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                '}';
    }
}
