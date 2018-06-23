package com.navigationdemo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.navigationdemo.R
import com.navigationdemo.activities.ProfileActivity
import com.navigationdemo.utils.Utils

class SignInFragment : Fragment(), View.OnClickListener {
    private lateinit var tetEmail: TextInputEditText
    private lateinit var tetPassword: TextInputEditText

    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_signin, container, false)
        initControl(view)
        return view
    }

    private fun initControl(view: View) {
        val btnLogin = view.findViewById<Button>(R.id.fragment_signin_btnLogin)
        val tvForgotPassword = view.findViewById<TextView>(R.id.fragment_signin_tvForgotPassword)
        val tvSignUp = view.findViewById<TextView>(R.id.fragment_signin_tvSignUp)
        tetEmail = view.findViewById(R.id.fragment_signin_tetEmail)
        tetPassword = view.findViewById(R.id.fragment_signin_tetPassword)
        tilEmail = view.findViewById(R.id.fragment_signin_tilEmail)
        tilPassword = view.findViewById(R.id.fragment_signin_tilPassword)

        btnLogin.setOnClickListener(this)
        tvForgotPassword.setOnClickListener(this)
        tvSignUp.setOnClickListener(this)

        arguments?.let {
            val safeArgs = SignInFragmentArgs.fromBundle(it)
            tetEmail.setText(safeArgs.emailId)
            tetPassword.setText(safeArgs.password)
        }
    }

    override fun onClick(view: View) {
        activity?.let { Utils.hideSoftKeyboard(it, view) }

        when (view.id) {
            R.id.fragment_signin_btnLogin -> validation()

            R.id.fragment_signin_tvForgotPassword -> {
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.forgotPasswordFragment)
            }

            R.id.fragment_signin_tvSignUp -> {
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.signUpFragment)
            }
        }
    }

    private fun validation() {
        tilEmail.error = null
        tilPassword.error = null

        if (tetEmail.text.toString().trim().isEmpty()) {
            tilEmail.error = getString(R.string.alert_email_blank)
            tetEmail.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(tetEmail.text).matches()) {
            tilEmail.error = getString(R.string.alert_email_invalid)
            tetEmail.requestFocus()
        } else if (tetPassword.text.toString().trim().isEmpty()) {
            tilPassword.error = getString(R.string.alert_password_blank)
            tetPassword.requestFocus()
        } else if (tetPassword.text.toString().trim().length <= 3 || tetPassword.text.toString().trim().length > 8) {
            tilPassword.error = getString(R.string.alert_password_invalid)
            tetPassword.requestFocus()
        } else {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}
