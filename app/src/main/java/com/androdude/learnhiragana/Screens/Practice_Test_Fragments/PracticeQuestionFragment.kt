package com.androdude.learnhiragana.Screens.Practice_Test_Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.androdude.learnhiragana.ModelClass.QuestionsAnswers
import com.androdude.learnhiragana.R
import com.androdude.learnhiragana.Screens.HomeActivity
import com.androdude.learnhiragana.Utils.UtilFunctions
import com.androdude.learnhiragana.databinding.FragmentPracticeQuestionBinding
import kotlinx.android.synthetic.main.practice_set_alert_dialog.view.*
import java.util.*
import kotlin.collections.ArrayList


class PracticeQuestionFragment : Fragment() {

    private var _binding : FragmentPracticeQuestionBinding ?= null
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

    private var questionNumber : Int = 0
    private var rightAnswers : Double = 0.0
    private var wrongAnswers : Double= 0.0
    private var displayWidth : Int = 0
    private var attempts : Int = 0
    private var scoreCounterColour : Int = 0
    private var numberOfQuestions : Int = 1




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPracticeQuestionBinding.inflate(inflater,null,false)


        soundPool = SoundPool(1, AudioManager.STREAM_MUSIC,0)
        sounds= ArrayList()
        handler= Handler()
        Random = Random()
        utilFunctions = UtilFunctions()
        questionsAnswersList=utilFunctions.getQuestions()




        layoutParams = binding.scoreCardLayout.getLayoutParams()
        binding.scoreCardLayout.visibility=View.GONE
        layoutParams.width = layoutParams.width + 100
        binding.scoreCardLayout.setLayoutParams(layoutParams)

        startPracticeDialog(binding)






        binding.answer1Button.setOnClickListener{
            println(questionNumber)
            checkAnswers(binding.answer1Button,binding)
        }
        binding.answer2Button.setOnClickListener{
            println(questionNumber)
            checkAnswers(binding.answer2Button,binding)



        }
        binding.answer3Button.setOnClickListener{
            println(questionNumber)
            checkAnswers(binding.answer3Button,binding)
        }




        return binding.root
    }

    private fun startPracticeDialog(binding: FragmentPracticeQuestionBinding) {
        binding.testParentLayout.visibility=View.GONE
        val view = LayoutInflater.from(activity).inflate(R.layout.practice_set_alert_dialog,null)
        val alertDialog = AlertDialog.Builder(activity).setView(view).create()
        alertDialog.show()
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
        view.startPracticeButton.setOnClickListener {
            if(!view.numberOfQuestions.text.isEmpty())
            {
                numberOfQuestions=Integer.parseInt(view.numberOfQuestions.text.toString())
                binding.testParentLayout.visibility=View.VISIBLE
                sounds=utilFunctions.setSounds(activity!!,soundPool)
                handler.postDelayed(Runnable {  playSound() },1)
                setQuestions(binding)
                alertDialog.dismiss()
            }
            else
            {
                Toast.makeText(activity,"Please Enter Number Of Questions You Want :)",Toast.LENGTH_SHORT).show()
            }

        }
        view.backButton.setOnClickListener {
            alertDialog.dismiss()
            activity!!.onBackPressed()
        }
    }

    private fun tryAgain(binding: FragmentPracticeQuestionBinding)
    {
        binding.testParentLayout.visibility=View.GONE
        val alertDialog = AlertDialog.Builder(activity)
            .setTitle("Great!! Lets try Again?")
            .setPositiveButton(
                "Try Again"
            ) { dialog, whichButton ->
                binding.testParentLayout.visibility=View.VISIBLE
                dialog.dismiss()
                startPracticeDialog(binding)
                // do something...
            }
            .setNegativeButton(
                "Home"
            ) { dialog, whichButton -> dialog.dismiss()
                activity!!.onBackPressed()
            }.setCancelable(false).create().show()


    }

    private fun setQuestions(binding: FragmentPracticeQuestionBinding) {
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



        binding.hearHiraganaButton.setOnClickListener{
            playSound()
        }
    }

    private fun setRandomAns(Button1: Button, Button2: Button)
    {
        val randomSet = Random.nextInt(3)
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

    private fun playSound() {
        soundPool.play(sounds[questionNumber],1f,1f,1,0,1f)
    }

    fun setNextQuestions(binding: FragmentPracticeQuestionBinding)
    {
        attempts=0
        disbaleButtons(binding)
        handler.postDelayed(Runnable {
            questionNumber=Random.nextInt(questionsAnswersList.size)
            playSound()
            binding.answer1Button.setBackgroundResource(R.color.colorNormal)
            binding.answer2Button.setBackgroundResource(R.color.colorNormal)
            binding.answer3Button.setBackgroundResource(R.color.colorNormal)
            enableVisibility(binding)
            setQuestions(binding)
            enableButtons(binding)
        },1000)

    }

    private fun enableButtons(binding: FragmentPracticeQuestionBinding) {
        binding.answer1Button.isEnabled=true
        binding.answer2Button.isEnabled=true
        binding.answer3Button.isEnabled=true
        binding.hearHiraganaButton.isEnabled=true
    }
    private fun enableVisibility(binding: FragmentPracticeQuestionBinding) {
        binding.answer1Button.visibility=View.VISIBLE
        binding.answer2Button.visibility=View.VISIBLE
        binding.answer3Button.visibility=View.VISIBLE
    }
    private fun disbaleButtons(binding: FragmentPracticeQuestionBinding) {
        binding.answer1Button.isEnabled=false
        binding.answer2Button.isEnabled=false
        binding.answer3Button.isEnabled=false
        binding.hearHiraganaButton.isEnabled=false
    }

    fun checkAnswers(ans : Button,binding: FragmentPracticeQuestionBinding)
    {

        if(numberOfQuestions > 1)
        {
            if(ans.text.toString().equals(questionsAnswersList[questionNumber].answer))
            {
                soundPool.play(sounds[questionsAnswersList.size],1f,1f,1,0,1f)
                scoreCounterColour++
                ans.setBackgroundResource(R.color.colorRight)
                if(attempts==0)
                {
                    rightAnswers++;
                }
                else if(attempts==1)
                {
                    rightAnswers+=0.5
                }


                changeScoreCard(binding)

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
                numberOfQuestions--
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

                        numberOfQuestions--
                        setNextQuestions(binding)
                        attempts=0
                    },500)

                }

            }
        }
        else
        {
            handler.postDelayed(Runnable {
                tryAgain(binding)
            },1500)
        }




    }

    fun changeScoreCard(binding: FragmentPracticeQuestionBinding)
    {



        if(layoutParams.width < 850)
        {
            scoreColorChange()
            layoutParams.width = layoutParams.width + 20
            binding.scoreCardLayout.setLayoutParams(layoutParams)
            binding.scoreCardLayout.visibility=View.VISIBLE

        }
        binding.scoreCardTextview.text=rightAnswers.toString()
    }

    fun scoreColorChange()
    {
        when(scoreCounterColour)
        {
            5->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color25))
            10->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color35))
            15->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color45))
            20->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color55))
            25->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color65))
            30->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color75))
            35->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color85))
            40->binding.scoreCardLayout.setBackgroundColor(getColor(activity!!,R.color.color95))
        }

    }




    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }



}