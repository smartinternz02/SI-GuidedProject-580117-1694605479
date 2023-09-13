package com.example.dics

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import com.example.dics.databinding.ActivityDiceGenerateBinding
import kotlin.random.Random

class DiceGenerateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiceGenerateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiceGenerateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        this.window.navigationBarColor = resources.getColor(R.color.white)

        binding.rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val randomNumber = Random.nextInt(1, 7)
        val numberWords = arrayOf("One", "Two", "Three", "Four", "Five", "Six")
        val drawableResource = when (randomNumber) {
            1 -> R.drawable.one
            2 -> R.drawable.two
            3 -> R.drawable.three
            4 -> R.drawable.four
            5 -> R.drawable.five
            else -> R.drawable.six
        }
        val resultWord = numberWords[randomNumber - 1]
        binding.diceText.text = resultWord
        binding.diceImageView.setImageResource(drawableResource)
    }
}