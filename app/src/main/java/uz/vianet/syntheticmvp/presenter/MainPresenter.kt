package uz.vianet.syntheticmvp.presenter

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.syntheticmvp.model.Post
import uz.vianet.syntheticmvp.network.RetrofitHttp
import uz.vianet.syntheticmvp.presenter.impls.MainPresenterImpl
import uz.vianet.syntheticmvp.view.MainView

class MainPresenter(var mainView: MainView) : MainPresenterImpl {
    override fun apiPostList() {
        //pb_loading.visibility = View.VISIBLE
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {

                mainView.onPostListSuccess(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                Log.e("@@@", t.message.toString())
                mainView.onPostListFailure(t.message.toString())
            }
        })
    }

    override fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                apiPostList()
                mainView.onPostDeleteSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.message.toString())
            }
        })
    }

}