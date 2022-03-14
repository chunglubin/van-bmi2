package com.tom.bmi2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tom.bmi2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val REQUEST_DISPLAY_BMI = 16
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: BmiViewModel
    var launcher = registerForActivityResult(NameContract()){ name ->
        Log.d(TAG, ": $name");
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
        viewModel = ViewModelProvider(this).get(BmiViewModel::class.java)
        viewModel.bmi.observe(this)  { bmi ->
            binding.tvBmi.setText(bmi.toString())
        }
        binding.bHelp.setOnClickListener {
            Log.d("MainActivity", "help clicked ");
        }
    }

    fun bmi(view: View) {
        var weight = binding.edWeight.text.toString().toFloat()
        var height = binding.edHeight.text.toString().toFloat()
        viewModel.set(weight, height)
    }

    class NameContract : ActivityResultContract<Float, String>() {
        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == RESULT_OK) {
                val name = intent?.getStringExtra(Extras.NAME)
                return name!!
            } else {
                return "No name"
            }
        }

        override fun createIntent(context: Context, input: Float?): Intent {
            val intent = Intent(context, ResultActivity::class.java)
                .putExtra(Extras.BMI, input)
            return intent
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, ": onActivityResult");
        if (requestCode == REQUEST_DISPLAY_BMI && resultCode == RESULT_OK) {
            Log.d(TAG, "back from ResultActivity");
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}