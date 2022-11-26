package uz.vianet.syntheticmvp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_details.*
import uz.vianet.syntheticmvp.R
import uz.vianet.syntheticmvp.model.Post
import uz.vianet.syntheticmvp.presenter.DetailPresenter
import uz.vianet.syntheticmvp.utils.Utils.toast
import uz.vianet.syntheticmvp.view.DetailView
import kotlinx.android.synthetic.main.activity_edit.*
class DetailsActivity : AppCompatActivity(), DetailView {

    var postId:Int = 0

    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initViews()
    }

    private fun initViews() {

        detailPresenter = DetailPresenter(this)
        val extras = intent.extras
        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            postId = extras.getInt("id")
            detailPresenter.apiLoadPost(postId)
        }
    }

    override fun onLoadSuccess(post: Post?) {
        tv_title_detail.setText(post!!.title.uppercase())
        tv_body_detail.setText(post.body)
    }

    override fun onLoadFailure(error: String) {
        toast(this,error)
    }
}