package com.task.spinnertask

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.task.spinnertask.databinding.ActivityMainBinding
import com.task.spinnertask.databinding.CustomdialogBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    var spinnerValue= mutableListOf("First","Second","Third")
   lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,spinnerValue)
    binding?.spDynamic?.adapter=arrayAdapter
    binding?.spDynamic?.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            //var selectedItem=binding?.spDynamic?.selectedItem as String
            //Toast.makeText(this@MainActivity,"Selected item $position $selectedItem",Toast.LENGTH_SHORT).show()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
    binding?.spStatic?.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
        binding?.btnAdd?.setOnClickListener {
            var dialogBinding=CustomdialogBinding.inflate(layoutInflater)
            var dialog=Dialog(this).apply{
                setContentView(dialogBinding.root)
                show()
            }
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnSubmit.setOnClickListener {
                if (dialogBinding.etName.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etName.error="Enter Anything"
                }else{
                    spinnerValue.add(dialogBinding.etName.text.toString().trim())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }
}