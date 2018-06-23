package com.navigationdemo.ui.fragment

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.navigationdemo.R
import com.navigationdemo.utils.Utils


class SignUpFragment : Fragment(), View.OnClickListener {
    private lateinit var tetEmail: TextInputEditText
    private lateinit var tetUsername: TextInputEditText
    private lateinit var tetPhoneNumber: TextInputEditText
    private lateinit var tetPassword: TextInputEditText

    private lateinit var tilUsername: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPhoneNumber: TextInputLayout
    private lateinit var tilPassword: TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        initControl(view)
        return view
    }

    private fun initControl(view: View) {
        val btnSignUp = view.findViewById<Button>(R.id.fragment_signup_btnSignUp)

        tetUsername = view.findViewById(R.id.fragment_signup_tetUsername)
        tetEmail = view.findViewById(R.id.fragment_signup_tetEmail)
        tetPhoneNumber = view.findViewById(R.id.fragment_signup_tetPhoneNumber)
        tetPassword = view.findViewById(R.id.fragment_signup_tetPassword)

        tilUsername = view.findViewById(R.id.fragment_signup_tilUsername)
        tilEmail = view.findViewById(R.id.fragment_signup_tilEmail)
        tilPhoneNumber = view.findViewById(R.id.fragment_signup_tilPhoneNumber)
        tilPassword = view.findViewById(R.id.fragment_signup_tilPassword)

        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        activity?.let { Utils.hideSoftKeyboard(it, view) }

        when (view.id) {
            R.id.fragment_signup_btnSignUp -> validation(view)
        }
    }

    private fun validation(view: View) {
        tilUsername.error = null
        tilEmail.error = null
        tilPhoneNumber.error = null
        tilPassword.error = null

        if (tetUsername.text.toString().trim().isEmpty()) {
            tilUsername.error = getString(R.string.alert_username_blank)
            tetUsername.requestFocus()
        } else if (tetUsername.text.toString().trim().length <= 3) {
            tilUsername.error = getString(R.string.alert_username_invalid)
            tetUsername.requestFocus()
        } else if (tetEmail.text.toString().trim().isEmpty()) {
            tilEmail.error = getString(R.string.alert_email_blank)
            tetEmail.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(tetEmail.text).matches()) {
            tilEmail.error = getString(R.string.alert_email_invalid)
            tetEmail.requestFocus()
        } else if (tetPhoneNumber.text.toString().trim().isEmpty()) {
            tilPhoneNumber.error = getString(R.string.alert_phone_number_blank)
            tetPhoneNumber.requestFocus()
        } else if (tetPhoneNumber.text.toString().trim().length != 10) {
            tilPhoneNumber.error = getString(R.string.alert_phone_number_invalid)
            tetPhoneNumber.requestFocus()
        } else if (tetPassword.text.toString().trim().isEmpty()) {
            tilPassword.error = getString(R.string.alert_password_blank)
            tetPassword.requestFocus()
        } else if (tetPassword.text.toString().trim().length <= 3 || tetPassword.text.toString().trim().length > 8) {
            tilPassword.error = getString(R.string.alert_password_invalid)
            tetPassword.requestFocus()
        } else {
            val action = SignUpFragmentDirections.action_signUpFragment_to_signInFragment()
            action.setEmailId(tetEmail.text.toString())
            action.setPassword(tetPassword.text.toString())
            val navigationController = Navigation.findNavController(view)
            navigationController.navigate(action)
        }
    }
}