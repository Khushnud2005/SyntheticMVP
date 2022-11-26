package uz.vianet.syntheticmvp.view

import uz.vianet.syntheticmvp.model.Post

interface CreateView {
    fun createPostSuccess(post: Post?)
    fun createPostFailure(error: String)
}