package com.example.sports_analytics.activities.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sports_analytics.R;
import com.example.sports_analytics.activities.models.VideoAnalysisResponse;
import com.example.sports_analytics.activities.network.ApiService;
import com.example.sports_analytics.activities.network.RetrofitClient;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Uri selectedVideoUri;
    private ProgressBar progressBar;
    private TextView tvStatus;
    private ApiService apiService;
    private Button btnAnalyzeVideo;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo ApiService
        apiService = RetrofitClient.getClient().create(ApiService.class);

        // Tham chiếu các thành phần giao diện
        videoView = findViewById(R.id.video_view);
        progressBar = findViewById(R.id.progress_bar);
        tvStatus = findViewById(R.id.tv_status);
        Button btnUploadVideo = findViewById(R.id.btn_upload_video);
        btnAnalyzeVideo = findViewById(R.id.btn_analyze_video);
        Button btnViewResults = findViewById(R.id.btn_view_results);
        Button btnExit = findViewById(R.id.btn_exit);

        // Thêm MediaController để điều khiển VideoView
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // *** Thiết lập video mặc định từ res/raw (ví dụ: myvideo.mp4) ***
        Uri defaultVideoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.default_video);
        videoView.setVideoURI(defaultVideoUri);
        videoView.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(MainActivity.this, "Không thể phát video mặc định. Vui lòng kiểm tra định dạng.", Toast.LENGTH_SHORT).show();
            return true;
        });
        videoView.setOnPreparedListener(mp -> {
            mp.setOnVideoSizeChangedListener((mediaPlayer, width, height) -> {
                // Tính toán kích thước VideoView theo tỷ lệ của video
                float videoProportion = (float) width / height;
                int screenWidth = videoView.getWidth();
                int screenHeight = videoView.getHeight();
                float screenProportion = (float) screenWidth / screenHeight;
                android.view.ViewGroup.LayoutParams lp = videoView.getLayoutParams();

                if (videoProportion > screenProportion) {
                    lp.width = screenWidth;
                    lp.height = (int) (screenWidth / videoProportion);
                } else {
                    lp.width = (int) (screenHeight * videoProportion);
                    lp.height = screenHeight;
                }
                videoView.setLayoutParams(lp);
            });
            videoView.setBackground(null);
            videoView.start();
        });

        // Đăng ký ActivityResultLauncher để chọn video từ thư viện
        ActivityResultLauncher<Intent> pickVideoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedVideoUri = result.getData().getData();
                        if (selectedVideoUri != null) {
                            videoView.setVideoURI(selectedVideoUri);
                            videoView.setOnErrorListener((mp, what, extra) -> {
                                Toast.makeText(MainActivity.this, "Không thể phát video. Vui lòng kiểm tra định dạng.", Toast.LENGTH_SHORT).show();
                                return true;
                            });
                            videoView.setOnPreparedListener(mp -> {
                                mp.setOnVideoSizeChangedListener((mediaPlayer, width, height) -> {
                                    float videoProportion = (float) width / height;
                                    int screenWidth = videoView.getWidth();
                                    int screenHeight = videoView.getHeight();
                                    float screenProportion = (float) screenWidth / screenHeight;
                                    android.view.ViewGroup.LayoutParams lp = videoView.getLayoutParams();
                                    if (videoProportion > screenProportion) {
                                        lp.width = screenWidth;
                                        lp.height = (int) (screenWidth / videoProportion);
                                    } else {
                                        lp.width = (int) (screenHeight * videoProportion);
                                        lp.height = screenHeight;
                                    }
                                    videoView.setLayoutParams(lp);
                                });
                                videoView.setBackground(null);
                                videoView.start();
                            });
                            Toast.makeText(MainActivity.this, "Đã tải video lên!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Không tìm thấy video!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Không có video nào được chọn!", Toast.LENGTH_SHORT).show();
                    }
                }
        );





        // Sự kiện nút Tải lên video
        btnUploadVideo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("video/*");
            pickVideoLauncher.launch(intent);
        });

        // Sự kiện nút Phân tích video
        btnAnalyzeVideo.setOnClickListener(v -> {
            if (selectedVideoUri == null) {
                Toast.makeText(MainActivity.this, "Vui lòng chọn video trước", Toast.LENGTH_SHORT).show();
                return;
            }
            uploadVideo();
        });

        // Sự kiện nút Xem kết quả
        btnViewResults.setOnClickListener(v -> {
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            String latestAnalysis = prefs.getString("latest_analysis", null);

            if (latestAnalysis != null) {
                Gson gson = new Gson();
                VideoAnalysisResponse response = gson.fromJson(latestAnalysis, VideoAnalysisResponse.class);
                showResults(response);
            } else {
                Toast.makeText(MainActivity.this, "Chưa có kết quả phân tích nào", Toast.LENGTH_SHORT).show();
            }
        });

        // Sự kiện nút Thoát
        btnExit.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Thoát ứng dụng", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void uploadVideo() {
        // Vô hiệu hóa nút phân tích để tránh gọi nhiều lần
        btnAnalyzeVideo.setEnabled(false);
        // Hiển thị dialog xử lý
        showProcessingDialog();
        progressBar.setVisibility(View.VISIBLE);
        tvStatus.setText("Đang tải lên video...");

        // Chuyển đổi Uri thành File
        File videoFile = getFileFromUri(selectedVideoUri);
        if (videoFile == null) {
            hideProcessingDialog();
            btnAnalyzeVideo.setEnabled(true);
            Toast.makeText(MainActivity.this, "Không thể đọc file video", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo request body cho file video
        RequestBody requestFile = RequestBody.create(MediaType.parse("video/*"), videoFile);
        MultipartBody.Part videoPart = MultipartBody.Part.createFormData("video", videoFile.getName(), requestFile);

        Toast.makeText(MainActivity.this,
                "Đang xử lý video, quá trình này có thể mất vài phút...",
                Toast.LENGTH_LONG).show();

        // Gọi API để upload video và nhận kết quả phân tích
        apiService.uploadVideo(videoPart, 0.3).enqueue(new Callback<VideoAnalysisResponse>() {
            @Override
            public void onResponse(Call<VideoAnalysisResponse> call, Response<VideoAnalysisResponse> response) {
                hideProcessingDialog();
                btnAnalyzeVideo.setEnabled(true);
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    VideoAnalysisResponse analysisResponse = response.body();
                    saveAnalysisResults(analysisResponse);
                    tvStatus.setText("Phân tích hoàn tất!");
                    Toast.makeText(MainActivity.this, "Phân tích video thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    tvStatus.setText("Lỗi: " + response.message());
                    Toast.makeText(MainActivity.this, "Lỗi khi phân tích video", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VideoAnalysisResponse> call, Throwable t) {
                hideProcessingDialog();
                btnAnalyzeVideo.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                tvStatus.setText("Lỗi kết nối: " + t.getMessage());
                Toast.makeText(MainActivity.this,
                        "Có lỗi xảy ra trong quá trình xử lý video",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProcessingDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý video, vui lòng đợi...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideProcessingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private File getFileFromUri(Uri uri) {
        try {
            String path = getRealPathFromUri(uri);
            if (path != null) {
                return new File(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return uri.getPath();
    }

    private void saveAnalysisResults(VideoAnalysisResponse response) {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(response);
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        prefs.edit().putString("latest_analysis", jsonResponse).apply();
    }

    private void showResults(VideoAnalysisResponse response) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("analysis_data", new Gson().toJson(response));
        startActivity(intent);
    }
}
