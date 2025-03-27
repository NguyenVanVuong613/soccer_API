package com.example.sports_analytics.activities.models;

import com.google.gson.annotations.SerializedName;

public class AdvancedMetrics {
    @SerializedName("avg_ball_speed")
    private double avgBallSpeed;

    @SerializedName("ball_coverage")
    private double ballCoverage;

    @SerializedName("max_ball_speed")
    private double maxBallSpeed;

    @SerializedName("pressure_events_count")
    private int pressureEventsCount;

    @SerializedName("team1_avg_depth")
    private double team1AvgDepth;

    @SerializedName("team1_avg_width")
    private double team1AvgWidth;

    @SerializedName("team1_coverage")
    private double team1Coverage;

    @SerializedName("team2_avg_depth")
    private double team2AvgDepth;

    @SerializedName("team2_avg_width")
    private double team2AvgWidth;

    @SerializedName("team2_coverage")
    private double team2Coverage;

    // Getter cho avgBallSpeed
    public double getAvgBallSpeed() {
        return avgBallSpeed;
    }

    // Setter cho avgBallSpeed
    public void setAvgBallSpeed(double avgBallSpeed) {
        this.avgBallSpeed = avgBallSpeed;
    }

    // Getter cho ballCoverage
    public double getBallCoverage() {
        return ballCoverage;
    }

    // Setter cho ballCoverage
    public void setBallCoverage(double ballCoverage) {
        this.ballCoverage = ballCoverage;
    }

    // Getter cho maxBallSpeed
    public double getMaxBallSpeed() {
        return maxBallSpeed;
    }

    // Setter cho maxBallSpeed
    public void setMaxBallSpeed(double maxBallSpeed) {
        this.maxBallSpeed = maxBallSpeed;
    }

    // Getter cho pressureEventsCount
    public int getPressureEventsCount() {
        return pressureEventsCount;
    }

    // Setter cho pressureEventsCount
    public void setPressureEventsCount(int pressureEventsCount) {
        this.pressureEventsCount = pressureEventsCount;
    }

    // Getter cho team1AvgDepth
    public double getTeam1AvgDepth() {
        return team1AvgDepth;
    }

    // Setter cho team1AvgDepth
    public void setTeam1AvgDepth(double team1AvgDepth) {
        this.team1AvgDepth = team1AvgDepth;
    }

    // Getter cho team1AvgWidth
    public double getTeam1AvgWidth() {
        return team1AvgWidth;
    }

    // Setter cho team1AvgWidth
    public void setTeam1AvgWidth(double team1AvgWidth) {
        this.team1AvgWidth = team1AvgWidth;
    }

    // Getter cho team1Coverage
    public double getTeam1Coverage() {
        return team1Coverage;
    }

    // Setter cho team1Coverage
    public void setTeam1Coverage(double team1Coverage) {
        this.team1Coverage = team1Coverage;
    }

    // Getter cho team2AvgDepth
    public double getTeam2AvgDepth() {
        return team2AvgDepth;
    }

    // Setter cho team2AvgDepth
    public void setTeam2AvgDepth(double team2AvgDepth) {
        this.team2AvgDepth = team2AvgDepth;
    }

    // Getter cho team2AvgWidth
    public double getTeam2AvgWidth() {
        return team2AvgWidth;
    }

    // Setter cho team2AvgWidth
    public void setTeam2AvgWidth(double team2AvgWidth) {
        this.team2AvgWidth = team2AvgWidth;
    }

    // Getter cho team2Coverage
    public double getTeam2Coverage() {
        return team2Coverage;
    }

    // Setter cho team2Coverage
    public void setTeam2Coverage(double team2Coverage) {
        this.team2Coverage = team2Coverage;
    }
}
