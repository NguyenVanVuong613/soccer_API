package com.example.sports_analytics.activities.models;

import com.google.gson.annotations.SerializedName;

public class VideoAnalysisResponse {
    @SerializedName("advanced_metrics")
    private AdvancedMetrics advancedMetrics;

    @SerializedName("match_stats")
    private MatchStats matchStats;

    @SerializedName("team_stats")
    private TeamStats teamStats;

    // Getter cho advancedMetrics
    public AdvancedMetrics getAdvancedMetrics() {
        return advancedMetrics;
    }

    // Setter cho advancedMetrics
    public void setAdvancedMetrics(AdvancedMetrics advancedMetrics) {
        this.advancedMetrics = advancedMetrics;
    }

    // Getter cho matchStats
    public MatchStats getMatchStats() {
        return matchStats;
    }

    // Setter cho matchStats
    public void setMatchStats(MatchStats matchStats) {
        this.matchStats = matchStats;
    }

    // Getter cho teamStats
    public TeamStats getTeamStats() {
        return teamStats;
    }

    // Setter cho teamStats
    public void setTeamStats(TeamStats teamStats) {
        this.teamStats = teamStats;
    }
}
