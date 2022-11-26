package uz.vianet.syntheticmvp.presenter

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.vianet.syntheticmvp.model.Post
import uz.vianet.syntheticmvp.network.RetrofitHttp
import uz.vianet.syntheticmvp.presenter.impls.DetailPresentImpl
import uz.vianet.syntheticmvp.view.DetailView

class DetailPresenter(var detailView: DetailView): DetailPresentImpl {
    override fun apiLoadPost(id: Int) {
        RetrofitHttp.postService.detailPost(id)
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {

                    if (response.body() != null) {
                        Log.d("@@@", response.body().toString())
                        detailView.onLoadSuccess(response.body()!!)

                    } else {
                        detailView.onLoadFailure(response.toString())
                        Log.d("@@@", response.toString())
                    }
                }

                override fun onFailure(call: Call<Post?>, t: Throwable) {
                    detailView.onLoadFailure(t.message.toString())
                    Log.d("@@@", t.toString())
                }
            })
    }

}