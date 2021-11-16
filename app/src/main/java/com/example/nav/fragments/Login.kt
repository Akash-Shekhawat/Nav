package com.example.nav.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nav.R
import androidx.navigation.fragment.findNavController
import com.example.nav.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*

class Login : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        login_BTN.setOnClickListener {
            if (isNameNotEmpty(name_ET.text.toString())) {
                val name = name_ET.text.toString()
                saveNameInSharedPref(name)
                navigateToHomeDestination()
            }
            else {
                name_ET.error = getString(R.string.name_must_enter_info)
            }
        }
    }

    private fun saveNameInSharedPref(name: String) {
        val sharedPreferences = activity?.getSharedPreferences(MainActivity.MY_PREFERENCES, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences?.edit()
        sharedPreferencesEditor?.putString(MainActivity.NAME_KEY, name)
        sharedPreferencesEditor?.apply()
    }

    private fun navigateToHomeDestination() {
        val action =
            LoginDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun isNameNotEmpty(name: String): Boolean {
        return name != ""
    }
}