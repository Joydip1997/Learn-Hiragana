package com.androdude.learnhiragana.Screens.Main_Test_Fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Point
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.androdude.learnhiragana.ModelClass.QuestionsAnswers
import com.androdude.learnhiragana.R
import com.androdude.learnhiragana.Utils.UtilFunctions
import com.androdude.learnhiragana.databinding.FragmentTestBinding
import kotlinx.android.synthetic.main.ask_details_alert_dialog.view.*
import java.util.*


class TestFragment : Fragment() {
    private  var _binding : FragmentTestBinding ?= null
    private val binding get() = _binding!!





    private lateinit var questionsAnswersList: ArrayList<QuestionsAnswers>
    private lateinit var soundPool : SoundPool
    private lateinit var sounds : ArrayList<Int>
    private lateinit var handler: Handler
    private lateinit var Random : Random
    private lateinit var utilFunctions : UtilFunctions
    private lateinit var windDisplay : Display
    private lateinit var size : Point
    private lateinit var layoutParams: ViewGroup.LayoutParams
    private lateinit var answerButtonList: ArrayList<Button>

    private lateinit var testFragmentListener: sendResultsInterface


    private var questionNumber : Int = 0
    private var totalQuestions : Int = 0
    private var totalTime : Int = 0
    private var isHard : Boolean = false
    private var rightAnswers : Double = 0.0
    private var wrongAnswers : Double= 0.0
    private var displayWidth : Int = 0
    private var attempts : Int = 0
    private var numberOfQuestions : Int = 1
    private lateinit var countDownTimer: CountDownTimer

    private var timeBucket : Int = 0
    private var isHold : Boolean = false



    interface sendResultsInterface
    {
        fun sendExamResults(rightAnswers : Double,wrongAnswers : Double)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTestBinding.inflate(inflater,null,false)
        utilFunctions= UtilFunctions()
        questionsAnswersList = utilFunctions.getQuestions()
        Random = Random()
        soundPool = SoundPool(1, AudioManager.STREAM_MUSIC,0)
        handler = Handler()
        sounds=ArrayList()
        sounds=utilFunctions.setSounds(activity!!,soundPool)

        startAlertBuilder(activity!!)








        binding.answer1Button.setOnClickListener{

            checkAnswers(binding.answer1Button,binding)
        }
        binding.answer2Button.setOnClickListener{

            checkAnswers(binding.answer2Button,binding)
        }
        binding.answer3Button.setOnClickListener{

            checkAnswers(binding.answer3Button,binding)
        }
        binding.answer4Button.setOnClickListener{

            checkAnswers(binding.answer4Button,binding)
        }




        return binding.root
    }




    private fun enableButtons(binding: FragmentTestBinding) {
        binding.answer1Button.isEnabled=true
        binding.answer2Button.isEnabled=true
        binding.answer3Button.isEnabled=true
    }
    private fun enableVisibility(binding: FragmentTestBinding) {
        binding.answer1Button.visibility=View.VISIBLE
        binding.answer2Button.visibility=View.VISIBLE
        binding.answer3Button.visibility=View.VISIBLE
    }
    private fun disbaleButtons(binding: FragmentTestBinding) {
        binding.answer1Button.isEnabled=false
        binding.answer2Button.isEnabled=false
        binding.answer3Button.isEnabled=false
    }


    private fun setQuestions(binding: FragmentTestBinding) {
        binding.hiraganaCharView.setImageDrawable(activity!!.getDrawable(questionsAnswersList[questionNumber].questionImages))


            val num = Random.nextInt(3)
            when (num)
            {
                0->
                {
                    binding.answer1Button.text = questionsAnswersList[questionNumber].answer
                    setRandomAns(binding.answer2Button,binding.answer3Button)
                }
                1->
                {
                    binding.answer2Button.text = questionsAnswersList[questionNumber].answer
                    setRandomAns(binding.answer1Button,binding.answer3Button)
                }
                2->
                {
                    binding.answer3Button.text = questionsAnswersList[questionNumber].answer
                    setRandomAns(binding.answer1Button,binding.answer2Button)

                }



            }





    }

    private fun setRandomAns(Button1: Button, Button2: Button)
    {
        val randomSet = Random.nextInt(2)
        when(randomSet+1)
        {
            1->{
                Button1.text=questionsAnswersList[questionNumber].answer1
                Button2.text=questionsAnswersList[questionNumber].answer2

            }
            2->{
                Button1.text=questionsAnswersList[questionNumber].answer2
                Button2.text=questionsAnswersList[questionNumber].answer3

            }
            3->{
                Button1.text=questionsAnswersList[questionNumber].answer3
                Button2.text=questionsAnswersList[questionNumber].answer1
            }
        }

    }

    fun setNextQuestions(binding: FragmentTestBinding)
    {
        attempts=0
        disbaleButtons(binding)
        handler.postDelayed(Runnable {
            questionNumber=Random.nextInt(questionsAnswersList.size)
            binding.answer1Button.setBackgroundResource(R.color.colorNormal)
            binding.answer2Button.setBackgroundResource(R.color.colorNormal)
            binding.answer3Button.setBackgroundResource(R.color.colorNormal)
            enableVisibility(binding)
            setQuestions(binding)
            enableButtons(binding)
        },1000)

    }

    fun checkAnswers(ans : Button,binding: FragmentTestBinding)
    {

        if(numberOfQuestions <= totalQuestions)
        {
            if(ans.text.toString().equals(questionsAnswersList[questionNumber].answer))
            {
                soundPool.play(sounds[questionsAnswersList.size],1f,1f,1,0,1f)
                ans.setBackgroundResource(R.color.colorRight)
                if(attempts==0)
                {
                    rightAnswers++;
                }
                else if(attempts==1)
                {
                    rightAnswers+=0.5
                }


                when(questionsAnswersList[questionNumber].answer)
                {
                    binding.answer1Button.text -> {
                        binding.answer2Button.setBackgroundResource(R.color.colorWrong)
                        binding.answer3Button.setBackgroundResource(R.color.colorWrong)

                    }
                    binding.answer2Button.text -> {
                        binding.answer1Button.setBackgroundResource(R.color.colorWrong)
                        binding.answer3Button.setBackgroundResource(R.color.colorWrong)

                    }
                    binding.answer3Button.text ->{
                        binding.answer1Button.setBackgroundResource(R.color.colorWrong)
                        binding.answer2Button.setBackgroundResource(R.color.colorWrong)

                    }
                }
                numberOfQuestions++
                setNextQuestions(binding)
            }
            else
            {
                soundPool.play(sounds[questionsAnswersList.size+1],1f,1f,1,0,1f)
                ans.setBackgroundResource(R.color.colorWrong)
                ans.visibility=View.GONE
                attempts++
                if(attempts==2)
                {
                    wrongAnswers++
                    when(questionsAnswersList[questionNumber].answer)
                    {
                        binding.answer1Button.text -> {
                            binding.answer1Button.setBackgroundResource(R.color.colorRight)

                        }
                        binding.answer2Button.text -> {
                            binding.answer2Button.setBackgroundResource(R.color.colorRight)


                        }
                        binding.answer3Button.text ->{
                            binding.answer3Button.setBackgroundResource(R.color.colorRight)


                        }
                    }

                    handler.postDelayed(Runnable {
                        numberOfQuestions++
                        setNextQuestions(binding)
                        attempts=0
                    },500)

                }

            }
        }
        else
        {
            //Go To Score Screen
            testFragmentListener.sendExamResults(rightAnswers,wrongAnswers)
        }


    }


    fun CountdownTimer(binding: FragmentTestBinding)
    {

        countDownTimer = object : CountDownTimer(totalTime*1000.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //  scoreColorChange(Integer.parseInt((millisUntilFinished / 1000).toString()),activity!!)  //Here is a bug

                binding.timerviewTextview.text = ("seconds remaining: " + millisUntilFinished / 1000)
                //here you can have your logic to set text to edittext
            }


            override fun onFinish() {

                testFragmentListener.sendExamResults(rightAnswers,wrongAnswers)
            }

        }.start()

    }



    fun startAlertBuilder(context: Activity)
    {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.ask_details_alert_dialog,null)
        val alertDialog = AlertDialog.Builder(context).setView(view).create()

        binding.mainTestRootLayout.visibility=View.GONE
        setQuestions(binding)

        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)

        view.easyLevel.setOnClickListener{
            view.examDetails.visibility=View.VISIBLE
            view.examDetails.text=getText(R.string.easy_level)
        }

        view.mediumLevel.setOnClickListener{
            view.examDetails.visibility=View.VISIBLE
            view.examDetails.text=getText(R.string.medium_level)

        }

        view.hardLevel.setOnClickListener{
            view.examDetails.visibility=View.VISIBLE
            view.examDetails.text=getText(R.string.hard_level)

        }



        view.applyButton.setOnClickListener {
            val id = view.radioButtonGroup.checkedRadioButtonId
            val button = view.findViewById<RadioButton>(id)


            when(button.id)
            {
                R.id.easyLevel -> {totalTime=20
                    totalQuestions=10}
                R.id.mediumLevel -> {totalTime=18
                    totalQuestions=15}
                R.id.hardLevel -> {
                    totalTime=20
                    totalQuestions=20}
            }

            alertDialog.dismiss()
            binding.mainTestRootLayout.visibility=View.VISIBLE

            CountdownTimer(binding)
        }

        view.backButton.setOnClickListener {
            alertDialog.dismiss()
            activity!!.onBackPressed()
        }


        alertDialog.show()

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is sendResultsInterface )
        {
            testFragmentListener =  context
        }
        else
        {
            throw  RuntimeException("Error")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if(countDownTimer!=null)
        {
            countDownTimer.cancel()
        }

    }






}