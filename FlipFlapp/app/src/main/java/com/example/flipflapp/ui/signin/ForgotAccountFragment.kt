package com.example.flipflapp.ui.signin
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flipflapp.databinding.FragmentForgotAccountBinding
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [ForgotAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgotAccountFragment : Fragment() {

    private var _binding: FragmentForgotAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(//Inflate
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentForgotAccountBinding.inflate(inflater, container, false)
        val view = binding.root

        fun genRandomNumber(): Int {
            //Returns a int that gives a 4 digit random number
            var numRandom = Random().nextInt((9999 - 1000)) + 1000
            return numRandom
        }

        //Listens for when the user presses the forgot button
        binding.btnForgotUsername.setOnClickListener {
            //When a button is press sets both buttons invisible.
            binding.btnForgotUsername.setVisibility(View.INVISIBLE)
            binding.btnForgotPassword.setVisibility(View.INVISIBLE)

            //Makes the enter email part visible
            binding.btnForgotAccountEnterEmail.setVisibility(View.VISIBLE)
            binding.editTextForgotAccountEmail.setVisibility(View.VISIBLE)
            binding.tvTeller.text = "Enter Email:"

            //Email  is stored into a variable
            val Email = binding.editTextForgotAccountEmail.text.toString()
            val DigitPasscode = genRandomNumber()

            binding.btnForgotAccountEnterEmail.setOnClickListener() {
                //Sends the Email
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto: ")
                    putExtra(Intent.EXTRA_EMAIL, Email)
                    putExtra(Intent.EXTRA_SUBJECT, "Flip Flapp 4 Digit Passcode")
                    putExtra(Intent.EXTRA_TEXT, "Your 4 Digit Passcode is $DigitPasscode")
                }
                try {
                    //start email intent
                    startActivity(Intent.createChooser(intent, "Choose Email Client..."))
                } catch (e: Exception) {
                    //if any thing goes wrong for example no email client application or any exception
                    //get and show exception message
                    binding.tvTeller.text = "Error Was Found"
                }
            }
        }
        //Listens for when the user presses the Forgot Password button
        binding.btnForgotPassword.setOnClickListener {
            //When a button is press sets both buttons invisible.
            binding.btnForgotUsername.setVisibility(View.INVISIBLE)
            binding.btnForgotPassword.setVisibility(View.INVISIBLE)

            //Email  is stored into a variable
            val Email = binding.editTextForgotAccountEmail.text.toString()
            //gets a  random number
            val DigitPasscode = genRandomNumber()

            binding.btnForgotAccountEnterEmail.setOnClickListener() {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto: ")
                    putExtra(Intent.EXTRA_EMAIL, Email)
                    putExtra(Intent.EXTRA_SUBJECT, "Flip Flapp 4 Digit Passcode")
                    putExtra(Intent.EXTRA_TEXT, "Your 4 Digit Passcode is $DigitPasscode")
                }
                try {
                    //start email intent
                    startActivity(Intent.createChooser(intent, "Choose Email Client..."))
                } catch (e: Exception) {
                    //if any thing goes wrong for example no email client application or any exception
                    //get and show exception message
                    binding.tvTeller.text = "Error Was Found"
                }
            }
        }
        //view was set equal to the binding.root
        return view
    }
}