package site.kirshin.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
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
}