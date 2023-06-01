package com.rubabe.zipcodeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rubabe.zipcodeapp.databinding.ActivityMainBinding
import com.rubabe.zipcodeapp.viewmodel.MainActivityViewModel
import com.rubabe.zipcodeapp.viewmodel.State

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.apply {
            countryTextView.visibility = View.GONE
            stateTextView.visibility = View.GONE
            cityTextView.visibility = View.GONE
        }
        binding.searchButton.setOnClickListener {
            viewModel.getZipCode(this, binding.zipCodeEditText.text.toString())
        }


        viewModel.state.observe(this) {

            when (it) {
                State.SUCCESS -> {
                    binding.countryTextView.apply {
                        visibility = View.VISIBLE
                        text = "country: ${viewModel.zipCodeLiveData.value?.country}"
                    }
                    binding.stateTextView.apply {
                        visibility = View.VISIBLE
                        text = "state: ${viewModel.zipCodeLiveData.value?.state}"
                    }
                    binding.cityTextView.apply {
                        visibility = View.VISIBLE
                        text = "city: ${viewModel.zipCodeLiveData.value?.city}"
                    }
                }

                State.ERROR -> {
                    binding.apply {
                        countryTextView.visibility = View.GONE
                        stateTextView.visibility = View.GONE
                        cityTextView.visibility = View.GONE
                    }
                    showErrorDialog("Zipcode is false")
                }
                else -> {}
            }
        }
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Error")
            .setMessage(error)
            .setPositiveButton("OK", null)
            .show()
    }
}