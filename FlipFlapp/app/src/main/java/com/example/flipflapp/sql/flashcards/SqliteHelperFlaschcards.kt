package com.example.flipflapp.sql.flashcards
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

class SqliteHelperFlashcards(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,  DATABASE_VERSION ) {


    companion object {
        //Object of the database
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "flashcards.db"
        private const val TBL_FLASHCARDS = "tbl_flashcards"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Creates a database
        val createTblFlashcards = ("CREATE TABLE " + TBL_FLASHCARDS + "("
                + TITLE + "TEXT," + DESCRIPTION + "TEXT,"+")")
        db?.execSQL(createTblFlashcards)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //Upgrades the database
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_FLASHCARDS")
        onCreate(db)
    }

    fun insertFlashcards(flc: FlashcardModel): Long {
        //Inserts a new flashcard into the database
        val db = this.writableDatabase
        val contentValues = ContentValues()

        //The content of the destriction
        contentValues.put(TITLE, flc.title)
        contentValues.put(DESCRIPTION, flc.description)

        val success = db.insert(TBL_FLASHCARDS, null, contentValues)
        db.close()
        return success
    }

    fun getAllFlashcards(): ArrayList<FlashcardModel> {
        //Gets all the flashcards and returns it into a list
        val flcList: ArrayList<FlashcardModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_FLASHCARDS"
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            // The cursor will try to select a query but if doesn't it returns
                //a empty list
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        //Variables for the flashcards.
        var title: String
        var description: String

        if (cursor.moveToFirst()){
            do{
                title = cursor.getColumnIndex("title").toString()
                description = cursor.getColumnIndex("description").toString()

                val flc = FlashcardModel(title = title, description= description)
            }while (cursor.moveToNext())
        }
        return flcList
    }
}