package com.itamazons.interest_in_chat.WebTask

import com.greylabsdev.pexwalls.data.dto.SearchResultDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

const val STANDARD_PAGE_SIZE = 15

interface ApiInterface {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("v1/search")
    fun searchPhotoByQuery(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = STANDARD_PAGE_SIZE
    ): Observable<SearchResultDto>

    @GET("v1/search")
    fun searchPhotoByQuerySingle(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = STANDARD_PAGE_SIZE
    ): Single<SearchResultDto>

    @GET("v1/curated")
    fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = STANDARD_PAGE_SIZE
    ): Call<SearchResultDto>

    @GET("photos/{id}pexels-photo-{id}.jpeg")
    fun getPhotoById(
        @Path("id") id: String,
        @Query("h") height: Int,
        @Query("w") width: Int
    )

    // For coroutines

    @GET("v1/search")
    fun searchPhotoByQueryCall(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = STANDARD_PAGE_SIZE
    ): Call<SearchResultDto>


    companion object {
        const val USER_LOGIN = "user/user_register_by_phno"
        const val UPDATE_USER = "user/update_profile"
        const val USER_UPDATE_PICTURES = "user/update_profileimage"
        const val GET_QUESTION = "question/getall_question"
        const val SET_ANSWER = "question/setall_answer"
        const val GETALL_USERS = "user/getallnear_users"
        const val UPDATE_INTREST = "user/update_interest_in"
        const val UPDATE_STATUS = "user/update_current_status"
        const val GET_USER_BY_AUTH = "user/get_userdetailbyauth"
        const val GET_USER_FRIEND_STATUS = "friend/getfriend_status"
        const val SEND_FRIEND_REQUEST = "friend/send_friend_request"
        const val GET_FRIEND_REQUEST = "friend/getfriend_requestlist"


    }
}