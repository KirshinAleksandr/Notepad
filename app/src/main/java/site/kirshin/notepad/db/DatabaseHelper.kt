package site.kirshin.notepad.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context,
    Database.DATABASE_NAME, null, Database.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        // выполнение запроса на создание таблицы в базе данных Sqlite
        db?.execSQL(Database.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // выполняем запрос на удаление старой таблицы
        db?.execSQL(Database.SQL_DELETE_TABLE);

        // заново создаем таблицу(ы) в базе данных
        onCreate(db)
    }
}