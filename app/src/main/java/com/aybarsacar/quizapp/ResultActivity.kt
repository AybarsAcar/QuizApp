package com.aybarsacar.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_result)

    // fetch the information from the intent
    val username = intent.getStringExtra(Constants.USER_NAME)
    val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
    val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

    tv_username.text = username;
    tv_score.text = "your Score is $correctAnswers out of $totalQuestions"


    btn_finish.setOnClickListener {

      // start over
      startActivity(Intent(this, MainActivity::class.java))
    }

  }
}