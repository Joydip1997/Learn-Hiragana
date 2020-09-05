package com.androdude.learnhiragana.Screens.Main_Test_Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androdude.learnhiragana.R
import com.androdude.learnhiragana.Screens.HomeActivity
import com.androdude.learnhiragana.databinding.FragmentScoreCardBinding


class ScoreCardFragment : Fragment() {


    private var _binding : FragmentScoreCardBinding ?= null
    private val binding get() = _binding!!


    private var rAnswers : Double = 0.0
    private var wAnswers : Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScoreCardBinding.inflate(inflater,null,false)

        if(arguments != null)
        {
            rAnswers=arguments!!.getDouble("RIGHT_ANSWERS")
            wAnswers=arguments!!.getDouble("WRONG_ANSWERS")
        }

        binding.rightAnswersTextview.text = rAnswers.toString()
        binding.wrongAnswersTextview.text = wAnswers.toString()

        binding.tryAgainButton.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainTestParentLayout,TestFragment()).commit()
        }

        binding.gotoHomeButton.setOnClickListener {
            activity!!.startActivity(Intent(activity!!,HomeActivity::class.java))
        }

        return binding.root
    }


}