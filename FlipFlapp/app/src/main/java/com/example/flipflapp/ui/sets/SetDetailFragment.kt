package com.example.flipflapp.ui.sets
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flipflapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [SetDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_detail, container, false)
    }

}