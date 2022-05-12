package com.example.flipflapp.ui.signin
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.flipflapp.R
import com.example.flipflapp.databinding.FragmentNewUserBinding
import com.example.flipflapp.sql.user.SQLiteHelper
import com.example.flipflapp.sql.user.UserModel


class NewUserFragment : Fragment() {
    //declares the variables
    private lateinit var edFirstName: EditText
    private lateinit var edLastName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var sqliteHelper: SQLiteHelper

    //Binding
    private var _binding: FragmentNewUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(//Inflate
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout {
        //super.onCreate(savedInstanceState)

        // Retrieve and inflate the layout for this fragment
        _binding = FragmentNewUserBinding.inflate(inflater, container, false)
        val view = binding.root
        initView()
        sqliteHelper = SQLiteHelper(requireActivity().applicationContext)
        binding.btnNewUserSave.setOnClickListener {
            addUser()
        }
        return view
    }

    private fun addUser() {
        //gets the user text and puts it into the database
        val firstname = edFirstName.text.toString()
        val lastname = edLastName.text.toString()
        val email = edEmail.text.toString()
        val username = edUsername.text.toString()
        val password = edPassword.text.toString()

        if (firstname.isEmpty() || email.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty() && binding.editNewUserPassword.text == binding.editNewUserConfrimPassword.text) {
            Toast.makeText(activity, "Please Enter required fields", Toast.LENGTH_SHORT).show()
            view?.findNavController()?.navigate(R.id.action_signInFragment_to_newUserFragment)
        } else {
            val usr = UserModel(
                firstname = firstname, lastname = lastname, email = email,
                username = username, password = password
            )
            //gets the user model status
            val status = sqliteHelper.insertUser(usr)
            Toast.makeText(activity, "User Added...", Toast.LENGTH_SHORT).show()
            clearEditText()
            //check insert success or not success

        }
    }

    private fun clearEditText() {
        // clears text to empty
        edFirstName.setText("")
        edLastName.setText("")
        edEmail.setText("")
        edUsername.setText("")
        edPassword.setText("")
        binding.editNewUserConfrimPassword.setText("")
        //resets the focus
        edFirstName.requestFocus()
    }

    private fun initView() {
        // Functions sets the binded variables equal to each other to make
        //code easier to read.
        edFirstName = binding.editNewUserFirstName
        edLastName = binding.editNewUserLastName
        edEmail = binding.editNewUserEmail
        edEmail = binding.editNewUserEmail
        edUsername = binding.editNewUserUsername
        edPassword = binding.editNewUserPassword
        edEmail = binding.editNewUserEmail
    }
}