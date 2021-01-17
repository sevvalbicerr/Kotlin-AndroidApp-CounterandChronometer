package com.example.clockapplication

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_LONG
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_counter.*

class Counter : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            //sayacı başlat
            var clock = object : CountDownTimer(20000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    textView2.setText("-->" + millisUntilFinished / 1000)
                }
                override fun onFinish() {
                    textView2.text = "Counter : 0"
                }
            }.start()
            button7.setOnClickListener {
                clock.cancel()
            }
        }
        button2.setOnClickListener {
            var directions=CounterDirections.actionCounterToStopwatch()
            Navigation.findNavController(it).navigate(directions)

        }
    }

}