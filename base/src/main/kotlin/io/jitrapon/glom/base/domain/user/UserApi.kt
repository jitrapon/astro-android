package io.jitrapon.glom.base.domain.user

import io.reactivex.Flowable
import retrofit2.http.GET

interface UserApi {

    @GET("")
    fun getUsers(): Flowable<UsersResponse>
}