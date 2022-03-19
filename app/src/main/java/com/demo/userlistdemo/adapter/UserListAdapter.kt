package com.demo.userlistdemo.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.userlistdemo.R
import com.demo.userlistdemo.data.UserModel
import kotlinx.android.synthetic.main.userlist.view.*

class UserListAdapter(val activity: Activity): RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    private var userList: List<UserModel>? = null


    fun setUserList(userList: List<UserModel>?) {
        this.userList = userList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.userlist, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListAdapter.MyViewHolder, position: Int) {
        holder.bind(userList?.get(position)!!, activity)
        val userList = userList?.get(position)
        holder.name.text = userList?.login
        holder.id.text = userList?.id.toString()
        holder.resposUrl.text = userList?.type

    }

    override fun getItemCount(): Int {
        if(userList == null)return 0
        else return userList?.size!!

    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){


        var name = view.tvName
        var id = view.tvId
        var resposUrl = view.tvReposUrl


        fun bind(data: UserModel, activity: Activity) {

            name = itemView.findViewById(R.id.tvName)
            id = itemView.findViewById(R.id.tvId)
            resposUrl = itemView.findViewById(R.id.tvReposUrl)

        }
    }
}