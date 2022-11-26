package uz.vianet.syntheticmvp.presenter.impls

import uz.vianet.syntheticmvp.model.Post


interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(post: Post)
}