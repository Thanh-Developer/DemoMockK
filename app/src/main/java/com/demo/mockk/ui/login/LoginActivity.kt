package com.demo.mockk.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.demo.mockk.R
import com.demo.mockk.databinding.ActivityLoginBinding
import com.demo.mockk.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by ThanhPQ
 */
class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeField()
    }

    private fun initView() {
        val activityMainBinding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        activityMainBinding.apply {
            viewModel = loginViewModel
            executePendingBindings()
        }
    }

    private fun observeField() {
        with(loginViewModel) {
            isLoginSuccess.observe(this@LoginActivity, Observer { isSuccess ->
                if (isSuccess != null) {
                    startMain(isSuccess)
                }
            })

            obsMessageError.observe(this@LoginActivity, Observer {
                if (it != null) {
                    Toast.makeText(this@LoginActivity, it, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun startMain(isLogin: Boolean) = if (isLogin) {
        startActivity(Intent(this, MainActivity::class.java))
    } else {
        Toast.makeText(this, "Login fail!", Toast.LENGTH_LONG).show()
    }

}
