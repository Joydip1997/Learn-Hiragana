package com.androdude.learnhiragana.Screens

import android.graphics.Point
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.view.Display
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.androdude.learnhiragana.ModelClass.QuestionsAnswers
import com.androdude.learnhiragana.R
import com.androdude.learnhiragana.Screens.Practice_Test_Fragments.PracticeQuestionFragment
import com.androdude.learnhiragana.Utils.UtilFunctions
import com.androdude.learnhiragana.databinding.ActivityTestBinding
import java.util.*
import kotlin.collections.ArrayList


class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager.beginTransaction().replace(R.id.parentLayout,PracticeQuestionFragment()).commit()
    }








}