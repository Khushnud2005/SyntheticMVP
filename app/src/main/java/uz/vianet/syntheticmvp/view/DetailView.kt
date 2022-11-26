package uz.vianet.syntheticmvp.view

import uz.vianet.syntheticmvp.model.Post

interface DetailView {
    fun onLoadSuccess(post: Post?)
    fun onLoadFailure(error: String)
}