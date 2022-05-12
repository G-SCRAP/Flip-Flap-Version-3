package com.example.flipflapp.sql.sets
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.flipflapp.sql.user.UserModel
import java.lang.Exception

class SqliteHelperSets(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        //The database object
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "sets.db"
        private const val TBL_SETS = "tbl_sets"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Creates the database for the Sets
        val createTblSets = ("CREATE TABLE " + TBL_SETS + "("
                + TITLE + "TEXT," + DESCRIPTION + "TEXT," + ")")
        db?.execSQL(createTblSets)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //Upgrades the Database
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_SETS")
        onCreate(db)
    }


    fun insertSets(set: SetModel): Long {
        //Inserts the sets into the database
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(TITLE, set.title)
        contentValues.put(DESCRIPTION, set.description)

        val success = db.insert(TBL_SETS, null, contentValues)
        db.close()
        return success
    }

    fun getAllSets(): ArrayList<SetModel> {
        //Gets all the sets returns it into a list
        val setList: ArrayList<SetModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_SETS"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var title: String
        var description: String

        if (cursor.moveToFirst()) {
            do {
                title = cursor.getColumnIndex("title").toString()
                description = cursor.getColumnIndex("description").toString()

                val set = SetModel(title = title, description = description)
            } while (cursor.moveToNext())
        }
        return setList
    }
}