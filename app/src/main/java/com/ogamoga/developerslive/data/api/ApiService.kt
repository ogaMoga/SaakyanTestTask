package com.ogamoga.developerslive.data.api

import com.ogamoga.developerslive.domain.model.ApiItem
import com.ogamoga.developerslive.domain.model.ApiPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val NEXT_PAGE_TEMPLATE = "{section}/{page}?json=true"
private const val RANDOM_ITEM = "random?json=true"

interface ApiService {
    @GET(NEXT_PAGE_TEMPLATE)
    fun getPage(@Path("section") section: String, @Path("page") page: Int): Call<ApiPage>

    @GET(RANDOM_ITEM)
    fun getRandomItem(): Call<ApiItem>
}
