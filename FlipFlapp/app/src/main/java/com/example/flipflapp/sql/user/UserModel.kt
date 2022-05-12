package com.example.flipflapp.sql.user
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import java.util.*

//The model for the user
data class UserModel (
    var id: Int = getAutoId(),
    var firstname: String = "",
    var lastname: String = "",
    var email: String = "",
    var username: String = "",
    var password: String = "",

    ){
    companion object{
        //The Auto increment for the ID data
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}
