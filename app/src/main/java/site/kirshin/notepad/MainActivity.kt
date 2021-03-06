package site.kirshin.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import site.kirshin.notepad.db.DataAdapter
import site.kirshin.notepad.db.DatabaseManager

class MainActivity : AppCompatActivity() {

    val databaseManager = DatabaseManager(this)
    val dataAdapter = DataAdapter(ArrayList())

    fun init() {
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = dataAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        // открываем подключение к базе данных
        databaseManager.open()
        //
        fillAdapter()
    }

    fun onClickNew(view: View) {
        // вызываем экран редактирования по клику на кнопку
        val i = Intent(this, EditActivity::class.java)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        // закрываем подключение к базе данных
        databaseManager.close()
    }

    fun fillAdapter() {
        dataAdapter.updateAdapter(databaseManager.read())
    }
}