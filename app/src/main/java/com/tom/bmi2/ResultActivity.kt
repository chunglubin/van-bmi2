package com.tom.bmi2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tom.bmi2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val TAG = ResultActivity::class.java.simpleName
    lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBmi()
        binding.bDone.setOnClickListener {
            val name = binding.edName.text.toString()
            val data = Intent()
            data.putExtra("NAME", name)
            setResult(RESULT_OK, data)
            finish()
        }
    }

    private fun showBmi() {
        val bmi = intent.getFloatExtra("BMI", 0f)
        Log.d(TAG, "BMI: $bmi");
        binding.bmiDisplay.text = bmi.toString()
    }
}