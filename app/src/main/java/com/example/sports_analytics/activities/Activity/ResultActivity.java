package com.example.sports_analytics.activities.Activity;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sports_analytics.R;
import com.example.sports_analytics.activities.models.VideoAnalysisResponse;
import com.google.gson.Gson;
public class ResultActivity extends AppCompatActivity {

    private TextView tvMatchStats;
    private TextView tvAdvancedMetrics;
    private TextView tvTeamStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Khởi tạo views
        tvMatchStats = findViewById(R.id.tv_match_stats);
        tvAdvancedMetrics = findViewById(R.id.tv_advanced_metrics);
        tvTeamStats = findViewById(R.id.tv_team_stats);

        // Lấy dữ liệu từ Intent
        String jsonResponse = getIntent().getStringExtra("analysis_data");
        if (jsonResponse != null) {
            Gson gson = new Gson();
            VideoAnalysisResponse response = gson.fromJson(jsonResponse, VideoAnalysisResponse.class);
            displayResults(response);
        }
    }

    private void displayResults(VideoAnalysisResponse response) {
        if (response == null) {
            Toast.makeText(this, "Không có dữ liệu phân tích", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Match Stats
        if (response.getMatchStats() != null) {
            String matchStats = String.format(
                    "Thông tin Video:\n\n" +
                            "Tổng số frame: %d\n" +
                            "Độ phân giải: %s\n",
                    response.getMatchStats().getTotalFrames(),
                    response.getMatchStats().getVideoResolution()
            );
            tvMatchStats.setText(matchStats);
        }

        // Advanced Metrics
        if (response.getAdvancedMetrics() != null) {
            String advancedMetrics = String.format(
                    "THỐNG KÊ NÂNG CAO:\n\n" +
                            "Tốc độ bóng trung bình: %.2f\n" +
                            "Tốc độ bóng tối đa: %.2f\n" +
                            "Độ phủ bóng: %.2f\n" +
                            "Số sự kiện áp lực: %d\n\n" +
                            "ĐỘI 1:\n" +
                            "- Độ sâu trung bình: %.2f\n" +
                            "- Độ rộng trung bình: %.2f\n" +
                            "- Độ phủ: %.2f\n\n" +
                            "ĐỘI 2:\n" +
                            "- Độ sâu trung bình: %.2f\n" +
                            "- Độ rộng trung bình: %.2f\n" +
                            "- Độ phủ: %.2f\n",
                    response.getAdvancedMetrics().getAvgBallSpeed(),
                    response.getAdvancedMetrics().getMaxBallSpeed(),
                    response.getAdvancedMetrics().getBallCoverage(),
                    response.getAdvancedMetrics().getPressureEventsCount(),
                    response.getAdvancedMetrics().getTeam1AvgDepth(),
                    response.getAdvancedMetrics().getTeam1AvgWidth(),
                    response.getAdvancedMetrics().getTeam1Coverage(),
                    response.getAdvancedMetrics().getTeam2AvgDepth(),
                    response.getAdvancedMetrics().getTeam2AvgWidth(),
                    response.getAdvancedMetrics().getTeam2Coverage()
            );
            tvAdvancedMetrics.setText(advancedMetrics);
        }

        // Team Stats
        if (response.getTeamStats() != null &&
                response.getTeamStats().getTeam1() != null &&
                response.getTeamStats().getTeam2() != null) {
            String teamStats = String.format(
                    "THỐNG KÊ ĐỘI BÓNG:\n\n" +
                            "ĐỘI 1:\n" +
                            "- Đường chuyền thành công: %d\n" +
                            "- Đường chuyền thất bại: %d\n" +
                            "- Độ chính xác đường chuyền: %.2f%%\n" +
                            "- Tỷ lệ kiểm soát bóng: %.2f%%\n\n" +
                            "ĐỘI 2:\n" +
                            "- Đường chuyền thành công: %d\n" +
                            "- Đường chuyền thất bại: %d\n" +
                            "- Độ chính xác đường chuyền: %.2f%%\n" +
                            "- Tỷ lệ kiểm soát bóng: %.2f%%\n",
                    response.getTeamStats().getTeam1().getSuccessfulPasses(),
                    response.getTeamStats().getTeam1().getFailedPasses(),
                    response.getTeamStats().getTeam1().getPassAccuracy(),
                    response.getTeamStats().getTeam1().getPossessionPercentage(),
                    response.getTeamStats().getTeam2().getSuccessfulPasses(),
                    response.getTeamStats().getTeam2().getFailedPasses(),
                    response.getTeamStats().getTeam2().getPassAccuracy(),
                    response.getTeamStats().getTeam2().getPossessionPercentage()
            );
            tvTeamStats.setText(teamStats);
        }
    }
}