package com.example.allapp

import android.os.CountDownTimer

class StopwatchTimer(private val onTick: (Long, Int) -> Unit) {
//переменные
    private var timer: CountDownTimer? = null
    private var paused = false
    private var pauseTime: Long = 0L
    private var startTime: Long = 0L
// функция для определения паузы
    fun start() {
        startTime = if (paused) {
            System.currentTimeMillis() - pauseTime
        } else {
            System.currentTimeMillis()
        }
        paused = false
    // штука которая выщитывает секунды
        timer = object : CountDownTimer(Long.MAX_VALUE, 10) {
            override fun onTick(millisUntilFinished: Long) {
                if (!paused) {
                    val elapsedMillis = System.currentTimeMillis() - startTime
                    val seconds = elapsedMillis / 1000
                    val milliseconds = (elapsedMillis % 1000).toInt()
                    onTick(seconds, milliseconds)
                }
            }
            override fun onFinish() = Unit
        }
        timer?.start()
    }
// метод паузы
    fun pause() {
        if (!paused) {
            pauseTime = System.currentTimeMillis() - startTime // выщитывает момент с кокого места остановились
            timer?.cancel()
            paused = true
        }
    }
// метод сброса
    fun reset() {
        timer?.cancel()
        timer = null
        paused = false
        pauseTime = 0L
        startTime = 0L
    }
// проверяет таймер
    fun isRunning(): Boolean {
        return timer != null && !paused
    }
}
