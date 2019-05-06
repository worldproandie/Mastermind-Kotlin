package com.andieheung.mastermind_kotlin_mvvm.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

import com.andieheung.mastermind_kotlin_mvvm.R
import com.andieheung.mastermind_kotlin_mvvm.ViewModelFactory

fun AppCompatActivity.createBtn( handler: View.OnClickListener, id: Int): Button {
    val btn = findViewById<Button>(id)
    btn.setOnClickListener(handler)
    return btn
}

fun AppCompatActivity.createAdapter(context: Context, arrayId: Int): ArrayAdapter<CharSequence> {
    val adapter = ArrayAdapter.createFromResource(
        context,
        arrayId, android.R.layout.simple_spinner_item
    )
    adapter.setDropDownViewResource(R.layout.setting_spinner_dropdown_item)
    return adapter
}

fun AppCompatActivity.createSpinner(
    viewId: Int, adapter: ArrayAdapter<*>,
    listener: AdapterView.OnItemSelectedListener
): Spinner {
    val spinner = findViewById<View>(viewId) as Spinner
    spinner.onItemSelectedListener = listener
    spinner.adapter = adapter
    return spinner
}

fun <T : ViewModel> AppCompatActivity.getViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application.applicationContext)).get(viewModelClass)
