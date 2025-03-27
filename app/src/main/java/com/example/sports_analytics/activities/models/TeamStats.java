package com.example.sports_analytics.activities.models;

import com.google.gson.annotations.SerializedName;

public class TeamStats {
    @SerializedName("team1")
    private Team team1;

    @SerializedName("team2")
    private Team team2;

    // Getter cho team1
    public Team getTeam1() {
        return team1;
    }

    // Setter cho team1
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    // Getter cho team2
    public Team getTeam2() {
        return team2;
    }

    // Setter cho team2
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public static class Team {
        @SerializedName("failed_passes")
        private int failedPasses;

        @SerializedName("pass_accuracy")
        private double passAccuracy;

        @SerializedName("possession_percentage")
        private double possessionPercentage;

        @SerializedName("successful_passes")
        private int successfulPasses;

        // Getter cho failedPasses
        public int getFailedPasses() {
            return failedPasses;
        }

        // Setter cho failedPasses
        public void setFailedPasses(int failedPasses) {
            this.failedPasses = failedPasses;
        }

        // Getter cho passAccuracy
        public double getPassAccuracy() {
            return passAccuracy;
        }

        // Setter cho passAccuracy
        public void setPassAccuracy(double passAccuracy) {
            this.passAccuracy = passAccuracy;
        }

        // Getter cho possessionPercentage
        public double getPossessionPercentage() {
            return possessionPercentage;
        }

        // Setter cho possessionPercentage
        public void setPossessionPercentage(double possessionPercentage) {
            this.possessionPercentage = possessionPercentage;
        }

        // Getter cho successfulPasses
        public int getSuccessfulPasses() {
            return successfulPasses;
        }

        // Setter cho successfulPasses
        public void setSuccessfulPasses(int successfulPasses) {
            this.successfulPasses = successfulPasses;
        }
    }
}
