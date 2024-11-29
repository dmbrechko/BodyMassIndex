package com.example.bodymassindex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bodymassindex.databinding.ActivityCalculatorBinding
import java.lang.Math.pow
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToInt

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            val height = intent.getIntExtra(KEY_HEIGHT, 0)
            val weight = intent.getIntExtra(KEY_WEIGHT, 0)
            val index = weight.toDouble() / ((height.toDouble() / 100.0).pow(2))
            val recommendations: String
            val drawableResId: Int
            when {
                index > 40 || index < 16 -> {
                    recommendations = getString(R.string.call_ambulance)
                    drawableResId = R.drawable.death
                }
                index < 18 -> {
                    recommendations = getString(R.string.gain_weight)
                    drawableResId = R.drawable.thin
                }
                index < 25 -> {
                    recommendations = getString(R.string.all_is_fine)
                    drawableResId = R.drawable.strong
                }
                else -> {
                    recommendations = getString(R.string.lose_weight)
                    drawableResId = R.drawable.fat
                }
            }
            indexTV.text = String.format(Locale.getDefault(),"%d", index.roundToInt())
            imageIV.setImageResource(drawableResId)
            recommendationsTV.text = recommendations
        }

    }

    companion object {
        const val KEY_HEIGHT = "key height"
        const val KEY_WEIGHT = "key weight"
    }
}