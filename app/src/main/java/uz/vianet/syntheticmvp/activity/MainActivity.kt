package uz.vianet.syntheticmvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import uz.vianet.syntheticmvp.R
import uz.vianet.syntheticmvp.presenter.MainPresenter
import uz.vianet.syntheticmvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import uz.vianet.syntheticmvp.adapter.PostAdapter
import uz.vianet.syntheticmvp.model.Post
import uz.vianet.syntheticmvp.utils.Utils.toast

class MainActivity : AppCompatActivity(), MainView {

    final val TAG = MainActivity::class.java.simpleName

    lateinit var mainPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mainPresenter = MainPresenter(this)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        floating.setOnClickListener { openCreateActivity() }

        mainPresenter.apiPostList()
        pb_loading.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            Log.d("@@@Extra",extras.getString("message")!!)
            toast(this, extras.getString("message")!!)
        }
    }



    private fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this, posts)
        recyclerView.setAdapter(adapter)
        pb_loading.visibility = View.GONE
    }
    fun openCreateActivity() {
        val intent = Intent(this@MainActivity, CreateActivity::class.java)
        launchCreateActivity.launch(intent)
    }

    var launchCreateActivity = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            pb_loading.visibility = View.VISIBLE
            mainPresenter.apiPostList()
            Toast.makeText(this@MainActivity, "New Post Created", Toast.LENGTH_LONG).show()
            // your operation....
        } else {
            Toast.makeText(this@MainActivity, "Operation canceled", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPostListSuccess(posts: ArrayList<Post>?) {
        refreshAdapter(posts!!)
    }

    override fun onPostListFailure(error: String) {
        Log.d(TAG,error)
        pb_loading.visibility = View.GONE
    }

    override fun onPostDeleteSuccess(posts: Post?) {
        mainPresenter.apiPostList()
    }

    override fun onPostDeleteFailure(error: String) {
        pb_loading.visibility = View.GONE
    }


}