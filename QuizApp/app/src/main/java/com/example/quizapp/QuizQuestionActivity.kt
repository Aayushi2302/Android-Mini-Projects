package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mQuestionList : ArrayList<Question>? = null
    private var mCurrentPosition : Int = 1
    private var mSelectedOption : Int = 0
    private var mUserName:String? = null
    private var mCorrectAnswer:Int = 0

    private var tvQuestion : TextView? = null
    private var ivImage: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress : TextView? = null

    private var optionOne : TextView? = null
    private var optionTwo : TextView? = null
    private var optionThree : TextView? = null
    private var optionFour : TextView? = null
    private var submitBtn : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)

        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)
        submitBtn = findViewById(R.id.submitBtn)

        optionOne?.setOnClickListener(this)  //This "this" is used in order to see if the function OnCreate() has OnClickListener()
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionList = Constants.getQuestions()

        if(mCurrentPosition == mQuestionList!!.size)
            submitBtn?.text = "FINISH"
        else
            submitBtn?.text = "SUBMIT"

        setQuestion()

    }

    private fun defaultOptionView(){

        var optionList = ArrayList<TextView>()

        optionOne?.let{
            optionList.add(0,it)
        }
        optionTwo?.let{
            optionList.add(1,it)
        }
        optionThree?.let{
            optionList.add(2,it)
        }
        optionFour?.let{
            optionList.add(3,it)
        }

        for(i in optionList){
            i.setTextColor(Color.parseColor("#6c757d"))
            i.typeface = Typeface.DEFAULT
            i.background = ContextCompat.getDrawable(
                this,
                R.drawable.custom_border
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOption:Int){

        defaultOptionView()

        mSelectedOption = selectedOption

        tv.setTextColor(Color.parseColor("#FF6200EE"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.custom_selected_border
        )
    }

    private fun setQuestion() {

        defaultOptionView()

        var currentQuestion: Question = mQuestionList!![mCurrentPosition - 1]
        tvQuestion?.text = currentQuestion.question
        ivImage?.setImageResource(currentQuestion.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "${mCurrentPosition}/${progressBar?.max}"
        optionOne?.text = currentQuestion.optionOne
        optionTwo?.text = currentQuestion.optionTwo
        optionThree?.text = currentQuestion.optionThree
        optionFour?.text = currentQuestion.optionFour
    }

    private fun answerView(selectedAnswer:Int, drawableView:Int){

        when(selectedAnswer){

            1->{
                optionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2->{
                optionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            3->{
                optionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            4->{
                optionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    override fun onClick(view: View?) {
       when(view?.id){

           R.id.optionOne -> {
               optionOne?.let{
                   selectedOptionView(it,1)
               }
           }
           R.id.optionTwo -> {
               optionTwo?.let{
                   selectedOptionView(it,2)
               }
           }
           R.id.optionThree-> {
               optionThree?.let{
                   selectedOptionView(it,3)
               }
           }
           R.id.optionFour-> {
               optionFour?.let{
                   selectedOptionView(it,4)
               }
           }

           R.id.submitBtn-> {

               if(mSelectedOption == 0){

                   mCurrentPosition++

                   when{
                       mCurrentPosition <= mQuestionList!!.size ->{
                           setQuestion()
                       }
                       else ->{
                           val intent = Intent(this,ResultActivity::class.java)
                           intent.putExtra(Constants.USER_NAME,mUserName)
                           intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList?.size)
                           intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer)
                           startActivity(intent)
                           finish()
                       }
                   }
               }else{

                   val question = mQuestionList?.get(mCurrentPosition-1)
                   if(question!!.correctAnswer != mSelectedOption){
                       answerView(mSelectedOption,R.drawable.custom_wrong)
                   }else{
                       mCorrectAnswer++
                   }
                   answerView(question!!.correctAnswer,R.drawable.custom_correct)

                   if(mCurrentPosition == mQuestionList!!.size)
                       submitBtn?.text = "FINISH"
                   else
                       submitBtn?.text = "GO TO NEXT QUESTION"

                   mSelectedOption = 0
               }
           }
       }
    }
}