package com.demo.userlistdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.userlistdemo.adapter.UserListAdapter
import com.demo.userlistdemo.data.UserModel
import com.demo.userlistdemo.viewmodel.MainActivityViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: UserListAdapter
    //var list:Array<String> = arrayOf("Samiul","Zahid","Nayona","Rakib","Mithun","Firoz")
    lateinit var autoTextView:AutoCompleteTextView
    lateinit var spinner:Spinner
    lateinit var txt:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        autoTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView);
       // spinner = findViewById(R.id.spinner)
        //txt = findViewById(R.id.textView)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
       // userListRecyclerview.layoutManager = LinearLayoutManager(this)
        //recyclerAdapter = UserListAdapter(this)
       // userListRecyclerview.adapter =recyclerAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer { userList ->
            if(userList != null) {

                /*recyclerAdapter.setUserList(userList) //to show the list
                recyclerAdapter.notifyDataSetChanged() //notify
                val languages = resources.getStringArray(R.array.Languages)
                var i:Int = 0
                var list = arrayOf(userList[i].login.toString(),userList[i].type.toString())
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
                autoTextView.setAdapter(adapter)*/

                val adapter = UserListAdapter(this, android.R.layout.simple_list_item_1, userList)
                autoCompleteTextView.setAdapter(adapter)
                autoCompleteTextView.threshold = 1

                autoCompleteTextView.setOnItemClickListener() { parent, _, position, id ->
                    val selectedEmp = parent.adapter.getItem(position) as UserModel?
                    autoCompleteTextView.setText(selectedEmp?.login)
                }



            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}





