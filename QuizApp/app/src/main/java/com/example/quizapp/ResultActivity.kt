package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val nameText:TextView = findViewById(R.id.nameText)
        val score:TextView = findViewById(R.id.score)
        val finishBtn:Button = findViewById(R.id.finishBtn)

        nameText.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        score.text = "You scored $correctAnswers out of $totalQuestions"

        finishBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}