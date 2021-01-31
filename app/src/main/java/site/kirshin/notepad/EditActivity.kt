package site.kirshin.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_edit.*
import site.kirshin.notepad.db.DatabaseManager

class EditActivity : AppCompatActivity() {

    val databaseManager = DatabaseManager(this)
    val imageRequestCode = 10
    var tempImageUri = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }

    override fun onResume() {
        super.onResume()
        // открываем подключение к базе данных
        databaseManager.open()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK && requestCode == imageRequestCode) {
            // устанавливаем uri картинки из выбранного интента с галереей
            ivPicture.setImageURI(data?.data)
            // сохраняем строку со ссылкой на изображение в переменную
            tempImageUri = data?.data.toString()
        }
    }

    fun onClickAddImage(view: View) {
        // делаем видимым блок с картинкой
        imageLayout.visibility = View.VISIBLE
        // делаем невидимой кнпку для появление блока картинки
        btnUpload.visibility = View.GONE
    }

    fun onClickDeleteImage(view: View) {
        // делаем невидимым блок с картинкой
        imageLayout.visibility = View.GONE
        // делаем видимой кнпку для появление блока картинки
        btnUpload.visibility = View.VISIBLE
    }

    fun onClickChooseImage(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, imageRequestCode)
    }

    fun onClickSave(view: View) {
        val title = edTitle.text.toString()
        val content = edContent.text.toString()

        if (title != "" && content != "") {
            databaseManager.insert(title, content, tempImageUri)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // закрываем подключение к базе данных
        databaseManager.close()
    }
}