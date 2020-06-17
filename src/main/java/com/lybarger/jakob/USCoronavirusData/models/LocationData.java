package com.lybarger.jakob.USCoronavirusData.models;


public class LocationData {

    private String countyAndState;
    private int totalCasesReported;

    public String getCountyAndState() {
        return countyAndState;
    }

    public void setCountyAndState(String countyAndState) {
        this.countyAndState = countyAndState.substring(0, countyAndState.length() - 4);
    }

    public int getTotalCasesReported() {
        return totalCasesReported;
    }

    public void setTotalCasesReported(int totalCasesReported) {
        this.totalCasesReported = totalCasesReported;
    }

    @Override
    public String toString() {
        return String.format("LocationStats{ County='%s', Current Cases='%d' }", getCountyAndState(), getTotalCasesReported());
    }
}
