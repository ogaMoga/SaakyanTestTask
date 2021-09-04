package com.ogamoga.developerslive.data.repository

import com.ogamoga.developerslive.data.api.ApiService
import com.ogamoga.developerslive.domain.model.ApiResponse
import com.ogamoga.developerslive.domain.model.SectionType
import com.ogamoga.developerslive.exception.LostConnectionException
import com.ogamoga.developerslive.exception.WrongResponseException

class RemoteRepository(
    private val apiService: ApiService
) {
    fun getNextPage(page: Int, sectionType: SectionType): ApiResponse {
        val response = getResponse(sectionType.name.lowercase(), page)

        if (response.isSuccessful) {
            return response.body()!!
        } else throw WrongResponseException(response.message())
    }

    private fun getResponse(section: String, page: Int) = try {
        apiService.getNextPage(section, page).execute()
    } catch (e: Exception) {
        throw LostConnectionException()
    }
}
