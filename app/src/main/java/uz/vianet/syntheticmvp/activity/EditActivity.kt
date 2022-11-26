package uz.vianet.syntheticmvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_edit.*
import uz.vianet.syntheticmvp.R
import uz.vianet.syntheticmvp.model.Post
import uz.vianet.syntheticmvp.presenter.EditPresenter
import uz.vianet.syntheticmvp.view.EditView

class EditActivity : AppCompatActivity(), EditView {

    lateinit var id: String

    lateinit var editPresenter: EditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initViews()
    }
    fun initViews(){
        val extras = intent.extras

        editPresenter = EditPresenter(this)

        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            et_userIdEdit.setText(extras.getString("user_id"))
            et_titleEdit.setText(extras.getString("title"))
            et_textEdit.setText(extras.getString("body"))
            id = extras.getString("id")!!
        }
        btn_submitEdit.setOnClickListener {
            val title = et_titleEdit.text.toString()
            val body = et_textEdit.text.toString().trim { it <= ' ' }
            val id_user = et_userIdEdit.text.toString().trim { it <= ' ' }
            if (title.isNotEmpty() && body.isNotEmpty() && id_user.isNotEmpty()){
                val post = Post(id.toInt(),id_user.toInt(), title, body)
                editPresenter.apiEditPost(post)
            }


        }
    }

    override fun onEditSuccess(post: Post) {
        val intent = Intent(this@EditActivity, MainActivity::class.java)
        intent.putExtra("message", post.title + " Edited")
        startActivity(intent)
    }

    override fun onEditFailure(error: String) {
        val intent = Intent(this@EditActivity, MainActivity::class.java)
        intent.putExtra("message", "Post Not Edited : $error")
        startActivity(intent)
    }
}