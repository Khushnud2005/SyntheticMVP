package uz.vianet.syntheticmvp.view

import uz.vianet.syntheticmvp.model.Post

interface EditView {
    fun onEditSuccess(post: Post)
    fun onEditFailure(error:String)
}