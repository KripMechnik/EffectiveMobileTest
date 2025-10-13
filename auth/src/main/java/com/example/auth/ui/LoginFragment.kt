package com.example.auth.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.auth.databinding.FragmentLoginBinding
import com.example.auth.di.AuthComponent
import com.example.auth.ui.navigation.AuthRouter
import com.example.core.di.utils.deleteComponent
import com.example.core.di.utils.getComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var authRouter: AuthRouter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val authComponent = getComponent<AuthComponent>()
        authComponent.inject(this)
        viewModel = ViewModelProvider(
            owner = this,
            factory = authComponent.viewModelFactory,
        )[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            authRouter.navigateToHomeFromAuth()
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.checkEmail(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.checkPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        viewModel.state.onEach {
            binding.btnLogin.isEnabled = it.emailStatus && it.passwordStatus
            Log.i("Status", "${it.emailStatus && it.passwordStatus}")
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        deleteComponent<AuthComponent>()
        super.onDestroy()
    }
}