package site.kirshin.notepad.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DatabaseManager(context: Context) {
    // создание экземпляра databaseHelper
    val databaseHelper = DatabaseHelper(context);
    // объявление поля db с типом SQLiteDatabase
    var db: SQLiteDatabase? = null;

    fun open() {
        // открытие подключения к базе данных
        db = databaseHelper.writableDatabase;
    }

    fun close() {
        databaseHelper.close();
    }

    fun insert(title: String, content: String) {
        // подготавливаем данные для записи в базу
        val values = ContentValues().apply {
            put(Database.COLUMN_NAME_TITLE, title);
            put(Database.COLUMN_NAME_CONTENT, content);
        }
        // вставляем подготовленные данные
        db?.insert(Database.TABLE_NAME, null, values);
    }

    fun read() :ArrayList<String> {
        // инициализируем пустой масив строк
        val list = ArrayList<String>();
        // достатем данные из базы
        val cursor = db?.query(
            Database.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null);

        with(cursor) {
            // выполняем только в том случае, если переменная cursor не равно null
            while(this?.moveToNext()!!) {
                // обходим данные и записываем в переменную text поле title
                val text = cursor?.getString(cursor.getColumnIndex(Database.COLUMN_NAME_TITLE));
                // добавляем в массив данные title, предваительно сделал преобразование к строке
                list.add(text.toString());
            }
        }

        // закрываем курсор в базе данных
        cursor?.close();

        return list;
    }
}