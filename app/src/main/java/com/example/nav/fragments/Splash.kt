package com.example.nav.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nav.MainActivity
import com.example.nav.R
import kotlinx.android.synthetic.main.fragment_splash.*

class Splash : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        splashNext_BTN.setOnClickListener {
            checkLogin()
        }
    }


    private fun checkLogin() {
        if (isUserLoggedIn())
            navigateToHomeDestination()
        else {
            navigateToLoginDestination()
        }
    }

    private fun navigateToLoginDestination() {
        findNavController().navigate(R.id.action_global_loginFragment)
    }

    private fun navigateToHomeDestination() {
        val action =
            SplashDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = activity?.getSharedPreferences(MainActivity.MY_PREFERENCES, Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString(MainActivity.NAME_KEY, "")
        return name != ""
    }
}