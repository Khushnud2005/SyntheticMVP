package uz.vianet.syntheticmvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import uz.vianet.syntheticmvp.R
import uz.vianet.syntheticmvp.model.Post
import uz.vianet.syntheticmvp.presenter.CreatePresenter
import uz.vianet.syntheticmvp.utils.Utils.toast
import uz.vianet.syntheticmvp.view.CreateView
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity(), CreateView {

    lateinit var createPresenter: CreatePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        initViews()
    }

    private fun initViews() {
        createPresenter = CreatePresenter(this)

        btn_submitCreate.setOnClickListener(View.OnClickListener {
            val title: String = et_titleCreate.getText().toString()
            val body: String = et_textCreate.getText().toString().trim { it <= ' ' }
            val id_user: String = et_userIdCreate.getText().toString().trim { it <= ' ' }

            if (title.isNotEmpty() && body.isNotEmpty() && id_user.isNotEmpty()){
                val post = Post(id_user.toInt(), title, body)
                createPresenter.apiPostCreate(post)
            }

        })
    }

    override fun createPostSuccess(post: Post?) {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        super@CreateActivity.onBackPressed()
    }

    override fun createPostFailure(error: String) {
        toast(this,error)
    }
}