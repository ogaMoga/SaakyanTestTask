package com.ogamoga.developerslive.data.api

import com.ogamoga.developerslive.domain.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val NEXT_PAGE_TEMPLATE = "{section}/{page}?json=true"

interface ApiService {
    @GET(NEXT_PAGE_TEMPLATE)
    fun getNextPage(@Path("section") section: String, @Path("page") page: Int): Call<ApiResponse>
}
