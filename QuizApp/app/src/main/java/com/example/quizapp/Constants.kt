package com.example.quizapp

object Constants {

    const val USER_NAME:String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_questions"
    const val CORRECT_ANSWERS:String = "correct_answers"

    fun getQuestions() : ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val q1 = Question(
            1, "Which country does this flag belong to?",
            R.drawable.canada,"Qatar","Chile","Canada","Colombia",
            3
        )
        questionList.add(q1)

        val q2 = Question(
            2, "Which country does this flag belong to?",
            R.drawable.argentina,"Argentina","Armenia","Austria","Syria",
            1
        )
        questionList.add(q2)

        val q3 = Question(
            3, "Which country does this flag belong to?",
            R.drawable.jordan,"Mexico","Armenia","Maldives","Jordan",
            4
        )
        questionList.add(q3)

        val q4 = Question(
            4, "Which country does this flag belong to?",
            R.drawable.south_africa,"Malta","South Africa","Indonesia","Qatar",
            2
        )
        questionList.add(q4)

        val q5 = Question(
            5, "Which country does this flag belong to?",
            R.drawable.india,"America","India","Austria","Syria",
            2
        )
        questionList.add(q5)

        val q6 = Question(
            6, "Which country does this flag belong to?",
            R.drawable.south_korea,"Algeria","Jordan","Austria","South Korea",
            4
        )
        questionList.add(q6)

        val q7 = Question(
            7, "Which country does this flag belong to?",
            R.drawable.australia,"United States of America","Australia","Austria","United Kingdom",
            2
        )
        questionList.add(q7)

        val q8 = Question(
            8, "Which country does this flag belong to?",
            R.drawable.israel,"Israel","Sweden","Turkey","Ukraine",
            1
        )
        questionList.add(q8)

        val q9 = Question(
            9, "Which country does this flag belong to?",
            R.drawable.iran,"Iraq","Iran","Serbia","Syria",
            2
        )
        questionList.add(q9)

        val q10 = Question(
            10, "Which country does this flag belong to?",
            R.drawable.nepal,"Bhutan","Romania","Maldives","Nepal",
            4
        )
        questionList.add(q10)

        return questionList
    }
}