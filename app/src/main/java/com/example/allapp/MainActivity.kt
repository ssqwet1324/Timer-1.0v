package com.example.allapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
// переменные дизайна
    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var startTimer: Button
    private lateinit var resetButton: Button
    private lateinit var stopwatchTimer: StopwatchTimer

    @SuppressLint("MissingInflatedId", "SetTextI18n", "DefaultLocale") // штука чтобы не возникало ошибок с текстом
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
// переменные
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        startTimer = findViewById(R.id.startTimer)
        resetButton = findViewById(R.id.resetButton)
// создаем секндомер
        stopwatchTimer = StopwatchTimer { seconds, milliseconds ->
            textView.text = String.format("%02d", seconds / 60)
            textView2.text = String.format("%02d", seconds % 60)
            textView3.text = String.format("%03d", milliseconds % 1000)
        }
// обработчик кнопок
        startTimer.setOnClickListener {
            if (stopwatchTimer.isRunning()) {
                stopwatchTimer.pause()
                startTimer.text = "Старт"
            } else {
                stopwatchTimer.start()
                startTimer.text = "Пауза"
            }

        resetButton.setOnClickListener{
            stopwatchTimer.reset()
            startTimer.text = "Старт"
            Toast.makeText(this, "Время сброшено", Toast.LENGTH_SHORT).show()
            textView.text = String.format("%02d", 0)
            textView2.text = String.format("%02d", 0)
            textView3.text = String.format("%03d", 0)
        }

        }
    }
}
