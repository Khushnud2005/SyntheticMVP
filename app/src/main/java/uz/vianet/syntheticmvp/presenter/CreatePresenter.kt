package uz.vianet.syntheticmvp.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.syntheticmvp.model.Post
import uz.vianet.syntheticmvp.network.RetrofitHttp
import uz.vianet.syntheticmvp.presenter.impls.CreatePresenterImpl
import uz.vianet.syntheticmvp.view.CreateView

class CreatePresenter(var createView: CreateView): CreatePresenterImpl {
    override fun apiPostCreate(post: Post?) {
        RetrofitHttp.postService.createPost(post!!).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                createView.createPostSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                createView.createPostFailure(t.message.toString())
            }
        })
    }

}