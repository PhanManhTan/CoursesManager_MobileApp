package com.example.myapplication.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.remote.AuthApi;
import com.example.myapplication.data.remote.RetrofitClient;
import com.example.myapplication.utils.SessionManager;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetNewPasswordActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "extra_email";

    private EditText etNewPassword, etConfirmPassword;
    private Button btnResetPassword;
    private TextView tvBackToLogin;
    private String userEmail;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        sessionManager = new SessionManager(this);
        userEmail = getIntent().getStringExtra(EXTRA_EMAIL);

        etNewPassword     = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword  = findViewById(R.id.btnResetPassword);
        tvBackToLogin     = findViewById(R.id.tvBackToLogin);

        btnResetPassword.setOnClickListener(v -> attemptReset());

        tvBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void attemptReset() {
        String password = etNewPassword.getText().toString().trim();
        String confirm  = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            etNewPassword.setError("Password is required");
            etNewPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etNewPassword.setError("Password must be at least 6 characters");
            etNewPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirm)) {
            etConfirmPassword.setError("Please confirm your password");
            etConfirmPassword.requestFocus();
            return;
        }
        if (!password.equals(confirm)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return;
        }

        btnResetPassword.setEnabled(false);
        btnResetPassword.setText("Updating...");

        AuthApi authApi = RetrofitClient.getClient(this).create(AuthApi.class);
        AuthApi.UpdatePasswordRequest request = new AuthApi.UpdatePasswordRequest(password);

        // Lưu ý: UpdatePassword cần token từ bước Verify OTP thành công.
        // RetrofitClient sẽ tự động đính kèm token nếu SessionManager đã lưu nó.

        authApi.updatePassword(request).enqueue(new Callback<AuthApi.AuthResponse>() {
            @Override
            public void onResponse(Call<AuthApi.AuthResponse> call, Response<AuthApi.AuthResponse> response) {
                btnResetPassword.setEnabled(true);
                btnResetPassword.setText("Reset Password");

                if (response.isSuccessful()) {
                    sessionManager.clear();
                    RetrofitClient.resetClient();

                    Toast.makeText(SetNewPasswordActivity.this,
                            "Password updated! Please login with your new password.",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SetNewPasswordActivity.this, LoginActivity.class);
                    intent.putExtra(LoginActivity.EXTRA_EMAIL, userEmail);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    String errorMsg = parseErrorMessage(response);
                    Toast.makeText(SetNewPasswordActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthApi.AuthResponse> call, Throwable t) {
                btnResetPassword.setEnabled(true);
                btnResetPassword.setText("Reset Password");
                Toast.makeText(SetNewPasswordActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String parseErrorMessage(Response<?> response) {
        try {
            if (response.errorBody() != null) {
                JSONObject json = new JSONObject(response.errorBody().string());
                String errorCode = json.optString("error_code", "");
                if ("same_password".equals(errorCode)) {
                    return "New password must be different from your current password.";
                }
                String msg = json.optString("msg", "");
                if (!msg.isEmpty()) return msg;
            }
        } catch (Exception ignored) {}
        if (response.code() == 401) return "Session expired. Please request a new password reset.";
        return "Failed to update password. Please try again.";
    }
}
