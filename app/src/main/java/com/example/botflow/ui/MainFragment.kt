package com.example.botflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.botflow.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment() : Fragment() {
    private lateinit var email: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        email = activity!!.intent.getStringExtra("email")!!
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI(BotListFragment())
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bot_list -> {
                    updateUI(BotListFragment())
                    true
                }
                R.id.account -> {
                    updateUI(AccountFragment(email))
                    true
                }
                else -> false
            }

        }
    }

    private fun updateUI(view: Fragment){
        val fragmentManager = activity!!.supportFragmentManager
        fragmentManager.beginTransaction().apply {
                replace(R.id.main_frameLayout, view)
            }.commit()
    }
}