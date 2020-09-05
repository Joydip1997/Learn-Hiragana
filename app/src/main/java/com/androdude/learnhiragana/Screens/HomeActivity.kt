package com.androdude.learnhiragana.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androdude.learnhiragana.ModelClass.QuestionsAnswers
import com.androdude.learnhiragana.R
import com.androdude.learnhiragana.databinding.ActivityHomeBinding
import com.androdude.learnhiragana.databinding.ActivityTestBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.playButton.setOnClickListener {
            val intent = Intent(this,MainTestActivity::class.java)
            startActivity(intent)
        }

        binding.practiceTestButton.setOnClickListener {
            val intent = Intent(this,TestActivity::class.java)
            startActivity(intent)

        }
    }
}

