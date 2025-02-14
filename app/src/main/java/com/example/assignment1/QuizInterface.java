package com.example.assignment1;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizInterface extends AppCompatActivity {
    TextView tvQuestion;
    RadioGroup rgOptions;
    Button btnPrevious, btnNext;
    int currentQuestionIndex = 0;
    int score = 0;
    String userName;
    Animation flip_in,flip_out;
    //array for storing questions
    String[] questions = {
            "What is the capital of Japan?",
            "What is the chemical symbol for Gold?",
            "In which year did World War II end?",
            "Which country is known as the \"Land of the Rising Sun\"?",
            "What is the largest planet in our solar system?",
            "Which company developed the video game \"Fortnite\"?",
            "Which element has the chemical symbol \"O\"?",
            "Which planet is known as the \"Red Planet\"?",
            "What is the largest organ in the human body?",
            "In what year did the Titanic sink?"
    };
//2D array for storing options of each question
    String[][] options = {
            {"Beijing", "Seoul", "Tokyo", "Bangkok"},
            {"Ag", "Pb", "Au", "Fe"},
            {"1939", "1945", "1914", "1960"},
            {"China", "South Korea", "Japan", "India"},
            {"Earth", "Jupiter", "Saturn", "Mars"},
            {"Blizzard", "Epic Games", "Valve", "Ubisoft"},
            {"Hydrogen", "Carbon", "Oxygen", "Nitrogen"},
            {"Venus", "Jupiter", "Mars", "Saturn"},
            {"Heart", "Lungs", "Skin", "Liver"},
            {"1935", "1912", "1920", "1900"}
    };
    //array of the correct answers of the questions
    int[] correctAnswers = {2, 2, 1, 2, 1, 1, 2, 2, 2, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_interface);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    init();
    userName = getIntent().getStringExtra("USER_NAME");
        loadQuestion();
        //setting all radiobuttons at default color and if anyone is clicked then it is set to light green color
        rgOptions.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < rgOptions.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rgOptions.getChildAt(i);
                radioButton.setBackgroundColor(getResources().getColor(R.color.default_radio_button_color));
            }
            if (checkedId != -1) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedRadioButton.setBackgroundColor(getResources().getColor(R.color.lightgreen1));
            }
        });
//onclick listener for Next button
        btnNext.setOnClickListener(v -> {

            if (rgOptions.getCheckedRadioButtonId() != -1) {
                int selectedOptionId = rgOptions.getCheckedRadioButtonId();
                RadioButton selectedOption = findViewById(selectedOptionId);

                int selectedOptionIndex = rgOptions.indexOfChild(selectedOption);
//if answer is correct then score will be incremented
                if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
                    score++;
                }
//check for questions length
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    loadQuestion();
                }
                //if all questions are answered then move to result activity using intent with 3 data arguments
                // which will be displayed in next activity
                else
                {
                    Intent intent = new Intent(QuizInterface.this, ResultActivity.class);
                    intent.putExtra("SCORE", score);
                    intent.putExtra("TOTAL_QUESTIONS", questions.length);
                    intent.putExtra("USER_NAME", userName);
                    startActivity(intent);
                }
//clearing clicked options of prev question
                rgOptions.clearCheck();
            }
            //check if user try to go next question without answering the current question
            else {
                Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
            }
        });
//if prev button is clicked then load the prev question
        btnPrevious.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                loadQuestion();
                tvQuestion.startAnimation(flip_out);
            }
        });

}
private void init()
{
    tvQuestion=findViewById(R.id.tvQuestion);
    btnPrevious=findViewById(R.id.btnPrevious);
    btnNext=findViewById(R.id.btnNext);
    rgOptions=findViewById(R.id.rgOptions);
    flip_in=AnimationUtils.loadAnimation(this,R.anim.flip_in);
    flip_out=AnimationUtils.loadAnimation(this,R.anim.flip_out);
}
//fucntion to display question and its option on textview and radio options field
private void loadQuestion() {

        tvQuestion.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < rgOptions.getChildCount(); i++) {
            ((RadioButton) rgOptions.getChildAt(i)).setText(options[currentQuestionIndex][i]);
            tvQuestion.startAnimation(flip_in);
            rgOptions.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.default_radio_button_color));
        }
        //check for prev button not being clicked for 1st question
        btnPrevious.setEnabled(currentQuestionIndex != 0);
    }

}