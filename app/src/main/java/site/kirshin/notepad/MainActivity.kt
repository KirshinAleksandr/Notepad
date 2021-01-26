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

    override fun onResume() {
        super.onResume();
        // открываем подключение к базе данных
        databaseManager.open();
    }

    fun onClickNew(view: View) {}

    override fun onDestroy() {
        super.onDestroy();
        // закрываем подключение к базе данных
        databaseManager.close();
    }
}