package com.example.nav.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.nav.MainActivity
import com.example.nav.R
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {
    private val MY_PREFERENCES = MainActivity.MY_PREFERENCES
    private val NAME_KEY = MainActivity.NAME_KEY
    private lateinit var navController: NavController
    private val TAG = Home::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        checkLogin()
        setListeners()
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(MainActivity.DESCRIPTION_KEY)
            ?.observe(viewLifecycleOwner, Observer {
                // Do something with result
                Log.i(TAG, "Description is $it")
                updateUIForDescription(it)
            })
    }

    private fun updateUIForDescription(description: String) {
        description_TV.text = description
    }
    private fun checkLogin() {
        if (isUserLoggedIn())

        else {
            Toast.makeText(activity, getString(R.string.name_must_enter_info), Toast.LENGTH_LONG)
                .show()
            navigateToLoginDestination()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = activity?.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString(NAME_KEY, "")
        return name != ""
    }

    private fun setListeners() {
        openAccount_BTN.setOnClickListener {
            navigateToAccountDestination()
        }

        addDescription_BTN.setOnClickListener {
            if (!isTitleEntered()) {
                val title = title_ET.text.toString()
                navigateToInputDescriptionDialogFragmentDestination(title)
            } else {
                title_ET.error = "You must enter title"
            }
        }
    }

    private fun navigateToInputDescriptionDialogFragmentDestination(title: String) {
        val action =
            HomeDirections.actionHomeFragmentToInputDescriptionDialogFragment(title) // Sending data using Safe args
        navController.navigate(action)
    }

    private fun isTitleEntered(): Boolean {
        return title_ET.text.toString().isEmpty()
    }

    private fun navigateToAccountDestination() {

        val action =
            HomeDirections.actionHomeFragmentToAccountFragment()
        navController.navigate(action)
    }

    private fun navigateToLoginDestination() {

        navController.navigate(R.id.action_global_loginFragment)
    }

}