package com.example.bodymassindex

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bodymassindex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            goBTN.setOnClickListener {
                try {
                    val height = heightET.text.toString().toInt()
                    val weight = weightET.text.toString().toInt()
                    if (height < 30 || height > 250) {
                        Toast.makeText(this@MainActivity,
                            R.string.strange_height, Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    if (weight < 2 || weight > 400) {
                        Toast.makeText(this@MainActivity,
                            R.string.strange_weight, Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    val intent = Intent(this@MainActivity, CalculatorActivity::class.java).apply {
                        putExtra(CalculatorActivity.KEY_HEIGHT, height)
                        putExtra(CalculatorActivity.KEY_WEIGHT, weight)
                    }
                    startActivity(intent)

                } catch (e: NumberFormatException) {
                    Toast.makeText(this@MainActivity, R.string.wrong_input, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}