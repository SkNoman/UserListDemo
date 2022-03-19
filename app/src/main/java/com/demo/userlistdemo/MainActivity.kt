package com.demo.userlistdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
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

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
            val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer { userList ->
            if(userList != null) {
                recyclerAdapter.setUserList(userList) //to show the list
                recyclerAdapter.notifyDataSetChanged() //notify
                //just remove above two lines

                val languages = resources.getStringArray(R.array.Languages) //list form string file for test


                //cannot use the userlist to the autocomplete text bar
               // Toast.makeText(applicationContext, "Id:"+id, Toast.LENGTH_LONG).show()
                //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languages)
                //problem is the whole list is attatched with the adapter



                Toast.makeText(this, "Name:"+userList[5].login, Toast.LENGTH_SHORT).show()
               // Toast.makeText(this, "TotalList"+userList[0].login, Toast.LENGTH_SHORT).show()
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,userList)
                autoTextView.setAdapter(adapter)


            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}