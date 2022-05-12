/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.flipflapp.ui.signin
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.flipflapp.R
import com.example.flipflapp.databinding.FragmentSignInBinding


/**
 * Entry fragment for the app. Displays a [RecyclerView] of letters.
 */
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //all the buttons in the view
    override fun onCreateView(
        //Inflate
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root

        //Listens to when Create Account TextView is clicked.
        binding.tvSignUpCreateAccount.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_signInFragment_to_newUserFragment)
        }
        //Listens to when Forgot Password TextView is clicked.
        binding.tvSignUpForgotPassword.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_signInFragment_to_forgotAccountFragment)
        }
        //Listens to when the Log in Button is clicked.
        binding.btnLogIn.setOnClickListener {
                view: View -> view.findNavController().navigate(R.id.action_signInFragment_to_homeFragment2)
        }
        //view was set equal to the binding.root
        return view
    }
}
