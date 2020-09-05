package com.androdude.learnhiragana.Utils

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.androdude.learnhiragana.ModelClass.QuestionsAnswers
import com.androdude.learnhiragana.R
import com.androdude.learnhiragana.databinding.FragmentPracticeQuestionBinding
import com.androdude.learnhiragana.databinding.FragmentTestBinding
import java.util.*
import kotlin.collections.ArrayList

class UtilFunctions
{
    private val questionsAnswersList = ArrayList<QuestionsAnswers>()
    private val Random = Random()
    private lateinit var sounds : ArrayList<Int>

     fun getQuestions() : ArrayList<QuestionsAnswers>  {
        questionsAnswersList.add(QuestionsAnswers(R.drawable.a,R.raw.a,"N","KI","NU","SE","A"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.i, R.raw.i,"N","WA","SA","N","I"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.u, R.raw.u,"KE","TSU","SU","N","U"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.e, R.raw.e,"TO","TE","TSU","N","E"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.o, R.raw.o,"MU","NI","NA","N","O"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ka, R.raw.ka,"MA","MI","A","N","KA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ki, R.raw.ki,"YU","YA","WO","N","KI"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ku, R.raw.ku,"TE","MU","O","N","KU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ke, R.raw.ke,"RA","ME","TSU","N","KE"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ko, R.raw.ko,"RI","KI","CHI","N","KO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.sa, R.raw.sa,"WA","TSU","RA","N","SA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.shi, R.raw.shi,"A","E","YU","N","SHI"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.su, R.raw.su,"A","NI","SA","N","SU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.se, R.raw.se,"RI","NI","WA","N","SE"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.so, R.raw.so,"KA","KO","NMA","N","SO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ta, R.raw.ta,"A","NI","NU","N","TA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.chi, R.raw.chi,"YA","WO","SA","N","CHI"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.tsu, R.raw.tsu,"FU","NI","NU","N","TSU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.te, R.raw.te,"A","NI","YU","N","TE"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.to, R.raw.to,"E","KI","KA","N","TO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.na, R.raw.na,"I","RI","NU","N","NA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ni, R.raw.ni,"SHI","O","N","N","NI"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.nu, R.raw.nu,"CHI","NA","U","N","NU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ne, R.raw.ne,"A","CHI","RE","N","NE"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.no, R.raw.no,"NE","KU","TSU","N","NO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ha, R.raw.ha,"MI","NI","SU","N","HA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.hi, R.raw.hi,"MA","HA","NU","N","HI"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.fu, R.raw.fu,"KU","SHI","RO","N","FU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.he, R.raw.he,"YO","CHI","YA","N","HE"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ho, R.raw.ho,"YU","FU","NU","N","HO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ma, R.raw.ma,"I","SE","KE","N","MA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.mi, R.raw.mi,"KA","TA","N","N","MI"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.mu, R.raw.mu,"SA","CHI","O","N","MU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.me, R.raw.me,"TE","SE","U","N","ME"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.mo, R.raw.mo,"NA","RI","RU","N","MO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ya, R.raw.yu,"A","N","WO","N","YA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.yo, R.raw.yo,"O","FU","SU","N","YO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.yu, R.raw.yu,"KO","SE","HE","N","YU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ra, R.raw.ra,"KU","TSU","SU","N","RA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ri, R.raw.ri,"FU","HO","MA","N","RI"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ru, R.raw.ru,"RE","SO","KA","N","RU"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.re, R.raw.re,"SHI","KI","NA","N","RE"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.ro, R.raw.ro,"SO","KE","N","N","RO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.wa, R.raw.wa,"SO","NO","RU","N","WA"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.wo, R.raw.o,"U","A","KE","N","WO"))
        questionsAnswersList.add(QuestionsAnswers(R.drawable.n, R.raw.n,"MA","SO","RE","N","N"))
        return questionsAnswersList

    }

      fun makeToast(context: Context,message:String)
      {
      Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
      }

   fun setSounds(context : Context,soundPool: SoundPool) : ArrayList<Int>
   {
      sounds = ArrayList()
      for(i in questionsAnswersList)
      {
         sounds.add(soundPool.load(context,i.sounds,1))
      }
      sounds.add(soundPool.load(context,R.raw.right_answer,1))
      sounds.add(soundPool.load(context,R.raw.wrong_answer,1))
      return sounds
   }





   fun enableButtons(button1 : Button,button2 : Button,button3 : Button) {
      button1.isEnabled=true
      button2.isEnabled=true
      button3.isEnabled=true

   }
   fun enableVisibility(button1 : Button,button2 : Button,button3 : Button) {
      button1.visibility= View.VISIBLE
      button2.visibility= View.VISIBLE
      button3.visibility= View.VISIBLE
   }
   fun disbaleButtons(button1 : Button,button2 : Button,button3 : Button) {
      button1.isEnabled=false
      button2.isEnabled=false
      button3.isEnabled=false
   }



}
