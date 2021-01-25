package site.kirshin.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import site.kirshin.notepad.db.DatabaseManager

class MainActivity : AppCompatActivity() {

    val databaseManager = DatabaseManager(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickSave(view: View) {
        tvNotes.text = "";
        // открываем подключение к базе данных
        databaseManager.open();
        // считываем данные и записываем в базу данных
        databaseManager.insert(edTitle.text.toString(), edContent.text.toString());
        // считываем данные из базы
        val list = databaseManager.read();

        for (item in list) {
            tvNotes.append(item);
            tvNotes.append("\n");
        }
    }

    override fun onDestroy() {
        super.onDestroy();
        // закрываем подключение к базе данных
        databaseManager.close();
    }
}