package com.aybarsacar.quizapp

object Constants {

  // constants that will be used to communicate data between activities
  const val USER_NAME: String = "user_name"
  const val TOTAL_QUESTIONS: String = "total_questions"
  const val CORRECT_ANSWERS: String = "correct_answers"


  fun getQuestions(): ArrayList<Question> {


    val q1 = Question(
      1, "What country does this flag belong to?", R.drawable.turkey,

      "Turkey",
      "Australia",
      "France",
      "Azerbaijan",
      1
    )

    val q2 = Question(
      2,
      "What country does this flag belong to?",
      R.drawable.australia,
      "Turkey",
      "Australia",
      "France",
      "Azerbaijan",
      2
    )

    return arrayListOf(q1, q2)
  }


}