package com.aybarsacar.quizapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


  @RequiresApi(Build.VERSION_CODES.R)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_main)


    // get rid of the status bar
    val controller = window.insetsController
    if (controller != null) {

      controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
      controller.systemBarsBehavior =
        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }


    startButton.setOnClickListener {

      if (name_field.text.toString().isEmpty()) {
        Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
      } else {
        val intent = Intent(this, QuizQuestionsActivity::class.java)

        // pass in data to the intent
        intent.putExtra(Constants.USER_NAME, name_field.text.toString())

        startActivity(intent);

        // finish the current view context
        finish()

      }

    }


  }
}

