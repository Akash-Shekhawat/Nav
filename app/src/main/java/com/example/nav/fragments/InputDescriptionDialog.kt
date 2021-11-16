package com.example.nav.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nav.fragments.InputDescriptionDialogArgs
import com.example.nav.MainActivity
import com.example.nav.R


class InputDescriptionDialog : DialogFragment() {

    val args: InputDescriptionDialogArgs by navArgs()
    lateinit var navController: NavController
    private val TAG = InputDescriptionDialog::class.java.simpleName
    lateinit var descriptionEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        navController = findNavController()
        // Get data using Safe args
        val title = args.dialogFragmentTitle

        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val dialogLayout = inflater.inflate(R.layout.fragment_input_description_dialog, null)
        descriptionEditText = dialogLayout.findViewById(R.id.description_ET)

        return builder

            .setView(dialogLayout)
            .setTitle(title)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.submit_btn_text)) { dialog, which ->
                getDescriptionFromDialog()
            }
            .create()
    }

    private fun getDescriptionFromDialog() {
        if (!isDescriptionEmpty()) {
            Log.i(TAG, "Description is ${descriptionEditText.text}")
            navController.previousBackStackEntry?.savedStateHandle?.set(
                MainActivity.DESCRIPTION_KEY,
                descriptionEditText.text.toString()
            )

        } else {
            navController.previousBackStackEntry?.savedStateHandle?.set(
                MainActivity.DESCRIPTION_KEY,
                getString(R.string.description_not_entered_info)
            )
            Toast.makeText(
                context,
                getString(R.string.description_not_entered_info),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isDescriptionEmpty(): Boolean {
        return descriptionEditText.text.toString() == ""
    }


}