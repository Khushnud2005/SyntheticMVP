package uz.vianet.syntheticmvp.view

import uz.vianet.syntheticmvp.model.Post

interface MainView {

    fun onPostListSuccess(posts:ArrayList<Post>?)
    fun onPostListFailure(error:String)

    fun onPostDeleteSuccess(posts:Post?)
    fun onPostDeleteFailure(error:String)
}