package com.navigationdemo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.navigationdemo.R
import com.navigationdemo.utils.Utils

class SignUpSignInFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up_sign_in, container, false)
        initControl(view)
        return view
    }

    private fun initControl(view: View) {
        val btnSignIn = view.findViewById<Button>(R.id.fragment_sign_up_sign_in_btnSignIn)
        val btnSignUp = view.findViewById<Button>(R.id.fragment_sign_up_sign_in_btnSignUp)

        btnSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        activity?.let { Utils.hideSoftKeyboard(it, view) }

        when (view.id) {
            R.id.fragment_sign_up_sign_in_btnSignIn -> {
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.signInFragment)
            }

            R.id.fragment_sign_up_sign_in_btnSignUp -> {
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.signUpFragment)
            }
        }
    }
}