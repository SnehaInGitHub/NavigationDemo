package com.navigationdemo.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.navigationdemo.NavigationDemoApp
import com.navigationdemo.R
import com.navigationdemo.utils.Utils

class ForgotPasswordFragment : Fragment(), View.OnClickListener {
    private lateinit var tetEmail: TextInputEditText

    private lateinit var tilEmail: TextInputLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        initControl(view)
        return view
    }

    private fun initControl(view: View) {
        val btnResetPassword = view.findViewById<Button>(R.id.fragment_forgot_password_btnResetPassword)
        tetEmail = view.findViewById(R.id.fragment_forgot_password_tetEmail)
        tilEmail = view.findViewById(R.id.fragment_forgot_password_tilEmail)

        btnResetPassword.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        activity?.let { Utils.hideSoftKeyboard(it, view) }

        when (view.id) {
            R.id.fragment_forgot_password_btnResetPassword -> validation()
        }
    }

    private fun validation() {
        tilEmail.error = null

        if (tetEmail.text.toString().trim().isEmpty()) {
            tilEmail.error = getString(R.string.alert_email_blank)
            tetEmail.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(tetEmail.text).matches()) {
            tilEmail.error = getString(R.string.alert_email_invalid)
            tetEmail.requestFocus()
        } else {
            val dialog = activity?.let { AlertDialog.Builder(it) }
            dialog?.let {
                it.setTitle(getString(R.string.app_name))
                it.setMessage(getString(R.string.alert_email_sent))
                it.setCancelable(false)
                it.setPositiveButton(getString(R.string.ok)) { dialogInterface, i ->
                    dialogInterface.dismiss()
                    activity?.onBackPressed()
                }
                it.create().show()
            }
        }
    }
}