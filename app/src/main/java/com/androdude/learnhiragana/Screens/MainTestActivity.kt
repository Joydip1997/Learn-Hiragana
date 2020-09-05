package com.androdude.learnhiragana.Screens

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androdude.learnhiragana.R
import com.androdude.learnhiragana.Screens.Main_Test_Fragments.ScoreCardFragment
import com.androdude.learnhiragana.Screens.Main_Test_Fragments.TestFragment
import com.androdude.learnhiragana.databinding.ActivityPracticeBinding

class MainTestActivity : AppCompatActivity(),TestFragment.sendResultsInterface{
    private lateinit var binding: ActivityPracticeBinding

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

         supportFragmentManager.beginTransaction().replace(R.id.mainTestParentLayout,
             TestFragment()
         ).commit()


    }


    override fun sendExamResults(rightAnswers: Double, wrongAnswers: Double) {
        println("DONE")
        val frag = ScoreCardFragment()
        val args = Bundle()
        args.putDouble("RIGHT_ANSWERS",rightAnswers)
        args.putDouble("WRONG_ANSWERS",wrongAnswers)
        frag.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.mainTestParentLayout,frag).commit()
    }




}