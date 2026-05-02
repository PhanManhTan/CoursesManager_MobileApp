package com.example.myapplication.activities.instructor;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.activities.common.AccountActivity;

public class InstructorAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
        finish();
    }
}
