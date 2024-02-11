package com.example.qixuanchen_temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var celsius: SeekBar
    private lateinit var fahrenheit: SeekBar
    private lateinit var celsiusVal: TextView
    private lateinit var fahrenheitVal: TextView
    private lateinit var message: TextView

    private var celsiusValue: Int = 0
    private var fahrenheitValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celsius = findViewById(R.id.celsius)
        fahrenheit = findViewById(R.id.fahrenheit)
        celsiusVal = findViewById(R.id.celsiusVal)
        fahrenheitVal = findViewById(R.id.fahrenheitVal)
        message = findViewById(R.id.message)

        celsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                celsiusValue = progress
                fahrenheitValue = (celsiusValue * 9 / 5) + 32
                fahrenheit.progress = fahrenheitValue
                celsiusVal.text = "Celsius: $celsiusValue°C"
                fahrenheitVal.text = "Fahrenheit: $fahrenheitValue°F"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                updateMessage(celsiusValue)
            }
        })

        fahrenheit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress < 32) {
                    fahrenheit.progress = 32
                    return
                }
                fahrenheitValue = progress
                celsiusValue = ((fahrenheitValue - 32) * 5) / 9
                celsius.progress = celsiusValue
                celsiusVal.text = "Celsius: $celsiusValue°C"
                fahrenheitVal.text = "Fahrenheit: $fahrenheitValue°F"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                updateMessage(celsiusValue)
            }
        })

    }

    private fun updateMessage(crossinline: Int) {
        if (celsiusValue <= 20) {
            message.text = "I wish it were warmer."
        } else {
            message.text =  "I wish it were colder."
        }
    }

}