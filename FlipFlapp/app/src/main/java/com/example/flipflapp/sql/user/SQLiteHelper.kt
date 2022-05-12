package com.example.flipflapp.sql.user
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,  DATABASE_VERSION ) {
    //Creates the object base
    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val ID = "id"
        private const val FIRSTNAME = "firstname"
        private const val LASTNAME = "lastname"
        private const val EMAIL = "email"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
    }
    //Creates the database
    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser = ("CREATE TABLE " + TBL_USER + "("
                + ID + "INTEGER PRIMARY KEY," + FIRSTNAME + "TEXT,"
                + LASTNAME + "TEXT," + EMAIL + "TEXT,"
                + USERNAME + "TEXT," + PASSWORD + "TEXT" + ")")
        db?.execSQL(createTblUser)
    }
    // Upgrades the database
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }


    //fun insertUser
    fun insertUser(usr: UserModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(ID, usr.id)
        contentValues.put(FIRSTNAME, usr.firstname)
        contentValues.put(LASTNAME, usr.lastname)
        contentValues.put(EMAIL, usr.email)
        contentValues.put(USERNAME, usr.username)
        contentValues.put(PASSWORD, usr.password)

        val success = db.insert(TBL_USER, null, contentValues)
        db.close()
        return success
    }

    fun getAllUser(): ArrayList<UserModel> {
        //Gets all the user in returns a array of the Usermodel
        val usrList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var firstname: String
        var lastname: String
        var email: String
        var username: String
        var password: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getColumnIndex("id").toInt()
                firstname = cursor.getColumnIndex("firstname").toString()
                lastname = cursor.getColumnIndex("lastname").toString()
                email = cursor.getColumnIndex("email").toString()
                username = cursor.getColumnIndex("username").toString()
                password = cursor.getColumnIndex("password").toString()

                val usr = UserModel(
                    id = id,
                    firstname = firstname,
                    lastname = lastname,
                    username = username,
                    password = password,
                    email = email,
                )
            } while (cursor.moveToNext())
        }
        return usrList
    }
}