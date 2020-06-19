package com.lybarger.jakob.USCoronavirusData.models;

public class DeathLocationData {

    private String countyAndState;
    private int totalDeaths;
    private int currentAndPrevDiff;

    public String getCountyAndState() {
        return countyAndState;
    }

    public void setCountyAndState(String countyAndState) {
        this.countyAndState = countyAndState.substring(0, countyAndState.length() - 4);;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getCurrentAndPrevDiff() {
        return currentAndPrevDiff;
    }

    public void setCurrentAndPrevDiff(int prevDayTotal) {
        this.currentAndPrevDiff = totalDeaths - prevDayTotal;
    }

    @Override
    public String toString() {
        return String.format("LocationStats{ County='%s', Current Deaths='%d' }", getCountyAndState(), getTotalDeaths());
    }
}
