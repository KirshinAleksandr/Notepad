package site.kirshin.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    val imageRequestCode = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK && requestCode == imageRequestCode) {
            // устанавливаем uri картинки из выбранного интента с галереей
            ivPicture.setImageURI(data?.data)
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
}