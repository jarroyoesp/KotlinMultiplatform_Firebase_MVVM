package com.jarroyo.kmp_mvvm_firebase.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jarroyo.kmp_mvvm_firebase.R
import com.jarroyo.kmp_mvvm_firebase.ui.adapter.RVAdapter
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import com.jarroyo.sharedcode.viewModel.firebase.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // View Model
    lateinit var mFirebaseViewModel: FirebaseViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: RVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configView()
        initViewModel()
    }

    private fun configView() {
        activity_main_button.setOnClickListener {
            mFirebaseViewModel.getFirebaseUserListFlow()
        }

        activity_main_button_add.setOnClickListener {
            createUser()
        }
    }

    private fun initViewModel() {
        mFirebaseViewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        observeViewModel()
    }

    private fun configRecyclerView(list: List<FirebaseUser>) {
        if (activity_main_rv.adapter == null) {
            mLayoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
            activity_main_rv.layoutManager = mLayoutManager

            mRvAdapter = RVAdapter(list)

            activity_main_rv.adapter = mRvAdapter

        } else {
            mRvAdapter?.setList(list)
            mRvAdapter?.notifyDataSetChanged()
        }
    }

    private fun createUser() {
        if (activity_main_et_user.text.toString().isNullOrBlank()) {
            Toast.makeText(this, "Enter a username please", Toast.LENGTH_SHORT).show()
        } else {
            mFirebaseViewModel.createUser(FirebaseUser(activity_main_et_user.text.toString()))
        }

    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    private fun observeViewModel() {
        mFirebaseViewModel.mGetFirebaseUserListLiveData.addObserver { getFirebaseUserListStateObserver(it) }
        mFirebaseViewModel.mCreateUserLiveData.addObserver { createFirebaseUserStateObserver(it) }
    }

    fun getFirebaseUserListStateObserver(state: GetFirebaseUserListState) {
        when (state) {
            is SuccessGetFirebaseUserListState -> {
                hideLoading()
                val response = state.response as Response.Success
                onSuccessFirebaseUserList(response.data)
            }

            is LoadingGetFirebaseUserListState -> {
                showLoading()
            }

            is ErrorGetFirebaseUserListState -> {
                hideLoading()
                val response = state.response as Response.Error
                showError(response.message)
            }
        }
    }

    fun createFirebaseUserStateObserver(state: CreateFirebaseUserState) {
        when (state) {
            is SuccessCreateFirebaseUserState -> {
                hideLoading()
                val response = state.response as Response.Success
                onSuccessFirebaseUserList(response.data)
            }

            is LoadingCreateFirebaseUserState -> {
                showLoading()
            }

            is ErrorCreateFirebaseUserState -> {
                hideLoading()
                val response = state.response as Response.Error
                showError(response.message)
            }
        }
    }
    /**
     * ON SUCCESS
     */
    private fun onSuccessFirebaseUserList(list: List<FirebaseUser>) {
        configRecyclerView(list)
    }

    /**
     * SHOW LOADING
     */
    private fun showLoading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
    }

    /**
     * HIDE LOADING
     */
    private fun hideLoading() {
    }

    /**
     * SHOW ERROR
     */
    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
