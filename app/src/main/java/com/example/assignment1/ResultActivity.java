package com.example.assignment1;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView tvResult1,tvResult2,tvResult3;
    Button btnShare,btnPrevious2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        //getting data from intent which was passed by previous activity
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        //setting it o display
        tvResult2.setText(userName);
        tvResult3.setText("Score: " + score + "/" + totalQuestions);

//onclick action for share button it should provide some platform to share out test result through send request
        btnShare.setOnClickListener(v -> {
            String shareText = "I scored " + score + "/" + totalQuestions + " in the MCQ Quiz App!";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(intent, "Share via"));
        });
//onclick action for previous button it should go back to home page
        btnPrevious2.setOnClickListener((v) -> {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        });


    }
    private void init()//initalizng textviews and buttons
    {
        tvResult1=findViewById(R.id.tvResult1);
        tvResult2=findViewById(R.id.tvResult2);
        tvResult3=findViewById(R.id.tvResult3);
        btnShare=findViewById(R.id.btnShare);
        btnPrevious2=findViewById(R.id.btnPrevious2);
    }
}