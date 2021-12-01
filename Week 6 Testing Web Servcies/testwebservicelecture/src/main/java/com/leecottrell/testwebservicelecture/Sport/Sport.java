package com.leecottrell.testwebservicelecture.Sport;

import java.util.Objects;

public class Sport {
    private String sportName;
    private String stadium;
    private String teamName;

    public Sport() {
    }

    public Sport(String sportName, String stadium, String teamName) {
        this.sportName = sportName;
        this.stadium = stadium;
        this.teamName = teamName;
    }

    public String getSportName() {
        return this.sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getStadium() {
        return this.stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Sport sportName(String sportName) {
        setSportName(sportName);
        return this;
    }

    public Sport stadium(String stadium) {
        setStadium(stadium);
        return this;
    }

    public Sport teamName(String teamName) {
        setTeamName(teamName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Sport)) {
            return false;
        }
        Sport sport = (Sport) o;
        return Objects.equals(sportName, sport.sportName) && Objects.equals(stadium, sport.stadium) && Objects.equals(teamName, sport.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportName, stadium, teamName);
    }

    @Override
    public String toString() {
        return "{" +
            " sportName='" + getSportName() + "'" +
            ", stadium='" + getStadium() + "'" +
            ", teamName='" + getTeamName() + "'" +
            "}";
    }

}
