package com.demo.userlistdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.userlistdemo.adapter.UserListAdapter
import com.demo.userlistdemo.data.UserModel
import com.demo.userlistdemo.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: UserListAdapter

    lateinit var autoTextView:AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         autoTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView);


        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        userListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = UserListAdapter(this)
        userListRecyclerview.adapter =recyclerAdapter

    }

    private fun initViewModel() {
            val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer { userList ->
            if(userList != null) {
                //recyclerAdapter.setUserList(userList)
                //recyclerAdapter.notifyDataSetChanged()
                val languages = resources.getStringArray(R.array.Languages) //list form string file





                //cannot use the userlist to the autocomplete text bar
                //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList)
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languages)
                autoTextView.setAdapter(adapter)
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}