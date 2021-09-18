package com.aybarsacar.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

  // state variables
  private var _currentPosition: Int = 1
  private var _questions: ArrayList<Question>? = null
  private var _selectedOption: Int = 0 // the position of the option that is selected

  private var _correctAnswerCounter = 0;

  private var _userName: String? = null


  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_quiz_questions)

    // store the username from the first activity
    _userName = intent.getStringExtra(Constants.USER_NAME)


    // get the questions
    _questions = Constants.getQuestions();

    setQuestion()

    option1.setOnClickListener(this)
    option2.setOnClickListener(this)
    option3.setOnClickListener(this)
    option4.setOnClickListener(this)


    button_submit.setOnClickListener(this)

  }

  override fun onClick(p0: View?) {
    when (p0?.id) {

      R.id.option1 -> handleOptionViewSelection(option1, 1)
      R.id.option2 -> handleOptionViewSelection(option2, 2)
      R.id.option3 -> handleOptionViewSelection(option3, 3)
      R.id.option4 -> handleOptionViewSelection(option4, 4)

      R.id.button_submit -> {

        if (_selectedOption == 0) {
          _currentPosition++

          when {
            _currentPosition <= _questions!!.size -> {
              setQuestion()
            }
            else -> {
              // move to the Result Screen
              val intent = Intent(this, ResultActivity::class.java)

              intent.putExtra(Constants.USER_NAME, _userName)
              intent.putExtra(Constants.CORRECT_ANSWERS, _correctAnswerCounter)
              intent.putExtra(Constants.TOTAL_QUESTIONS, _questions!!.size)

              // start the intent activity
              startActivity(intent)
            }
          }
        } else {
          // we have an option position
          val question = _questions?.get(_currentPosition - 1)

          if (question!!.correctAnswer != _selectedOption) {
            answerView(_selectedOption, R.drawable.wrong_option_background)
          } else {
            _correctAnswerCounter++
          }

          answerView(question.correctAnswer, R.drawable.correct_option_background)

          if (_currentPosition == _questions!!.size) {
            button_submit.text = "Finish"
          } else {
            button_submit.text = "Go to the next quesiton"

          }

          _selectedOption = 0
        }
      }
    }
  }

  private fun setQuestion() {

    val currentQuestion = _questions!!.get(_currentPosition - 1)


    // initialise the buttons to default view
    defaultOptionsView()

    // set the default button text
    button_submit.text = "Submit"



    progress_bar.max = _questions!!.size;
    progress_bar.progress = _currentPosition;
    progress_text.text = "$_currentPosition / ${progress_bar.max}"


    question_id.text = currentQuestion.question;
    question_image.setImageResource(currentQuestion.image)
    option1.text = currentQuestion.option1
    option2.text = currentQuestion.option2
    option3.text = currentQuestion.option3
    option4.text = currentQuestion.option4


  }


  private fun defaultOptionsView() {
    val options = ArrayList<TextView>()

    options.add(0, option1)
    options.add(1, option2)
    options.add(2, option3)
    options.add(3, option4)

    for (option in options) {
      option.setTextColor(Color.parseColor("#9D9D9D"))
      option.typeface = Typeface.DEFAULT;
      option.background = ContextCompat.getDrawable(this, R.drawable.default_background_with_border)
    }
  }


  private fun answerView(answer: Int, drawableView: Int) {
    when (answer) {
      1 -> option1.background = ContextCompat.getDrawable(this, drawableView)
      2 -> option2.background = ContextCompat.getDrawable(this, drawableView)
      3 -> option3.background = ContextCompat.getDrawable(this, drawableView)
      4 -> option4.background = ContextCompat.getDrawable(this, drawableView)
    }
  }

  private fun handleOptionViewSelection(textView: TextView, selectedOption: Int) {

    // reset the other buttons
    defaultOptionsView()

    // set the selected button's design
    _selectedOption = selectedOption

    textView.setTextColor(Color.parseColor("#150050"))
    textView.setTypeface(textView.typeface, Typeface.BOLD)
    textView.background = ContextCompat.getDrawable(this, R.drawable.selected_background_with_border)

  }
}