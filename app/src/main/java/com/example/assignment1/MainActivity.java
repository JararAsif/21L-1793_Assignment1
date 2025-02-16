package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
//hooks for editText,animations and buttons
    EditText etName;
    Animation slide_in_right,slide_out_left;
    Button btnStartQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnStartQuiz.setOnClickListener((v -> {
            String name=etName.getText().toString().trim();
            if(name.isEmpty()) //Error incase of empty text field
            {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
            else if(!isValidName(name))
            {
                Toast.makeText(this, "Name should only contain letters", Toast.LENGTH_SHORT).show();
            }

            else//moving from main activity to Quiz display class with the user name
            {
                Intent intent=new Intent(this,QuizInterface.class);
                intent.putExtra("USER_NAME", name);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_left);
                startActivity(intent);

            }
        }));
    }
    private void init()//initalizinf all buttons,texts and animations
    {
        etName=findViewById(R.id.etName);
        btnStartQuiz=findViewById(R.id.btnStartQuiz);
        AnimationUtils.loadAnimation(this,R.anim.slide_right);
        AnimationUtils.loadAnimation(this,R.anim.slide_left);
    }
    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+"); // Allows spaces between names
    }

}