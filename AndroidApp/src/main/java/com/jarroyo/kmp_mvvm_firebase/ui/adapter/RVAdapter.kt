package com.jarroyo.kmp_mvvm_firebase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarroyo.kmp_mvvm_firebase.R
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import kotlinx.android.synthetic.main.item_rv_rv_firebase_user.view.*

class RVAdapter(
    private var mList: List<FirebaseUser>? = listOf<FirebaseUser>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_rv_firebase_user, parent, false)
        return GitHubViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as GitHubViewHolder
        viewHolder.bind(
            position,
            mList?.get(position)
        )
    }

    class GitHubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            position: Int,
            gitHubRepo: FirebaseUser?
        ) = with(itemView) {
            item_rv_github_repo_list_tv.text = gitHubRepo?.name
        }
    }

    fun setList(list: List<FirebaseUser>) {
        mList = list
    }

    data class Item(val position: Int, val gitHubRepo: FirebaseUser)
}

