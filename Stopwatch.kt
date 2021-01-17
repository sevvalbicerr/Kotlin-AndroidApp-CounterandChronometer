package com.example.clockapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_stopwatch.*


class Stopwatch : Fragment() {
    var handler=Handler(Looper.myLooper()!!)
    lateinit var runnable: Runnable
    var numberR=0
    var pausedNumber=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stopwatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button3.setOnClickListener {
            //kronometre
            runnable= object : Runnable{
                //Runnable kullanmak sadece yazdığımız run() fonksiyonundaki number artışı için bir çalışma zamanını akif etmek
                //handler'ı da runnable kullanabilmek için kullanıyoruz.
                override fun run() {
                    numberR=numberR+1
                    handler.postDelayed(runnable, 1000)
                    textView4.text= "Time : ${numberR}"
                }
            }
            handler.post(runnable)
        }
        pause.setOnClickListener {
            handler.removeCallbacks(runnable)
            pausedNumber=numberR
            textView4.text="Time : ${pausedNumber}"
        }
        resume.setOnClickListener {
            runnable= object : Runnable{
                //Runnable kullanmak sadece yazdığımız run() fonksiyonundaki number artışı için bir çalışma zamanını akif etmek
                //handler'ı da runnable kullanabilmek için kullanıyoruz.
                override fun run() {
                    pausedNumber=pausedNumber+1
                    handler.postDelayed(runnable, 1000)
                    textView4.text= "Time : ${pausedNumber}"
                }
            }
            handler.post(runnable)
        }
        button5.setOnClickListener {
            handler.removeCallbacks(runnable)
            numberR=0
            textView4.text="Time : ${numberR}"
        }
        button4.setOnClickListener {
            //sayaç'a dönecek
            val action=StopwatchDirections.actionStopwatchToCounter()//sayaç'a dönmemizi sağlayacak navigation kodu burası
            Navigation.findNavController(it).navigate(action)
        }
    }
}