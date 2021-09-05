package com.ogamoga.developerslive.data.repository

import com.ogamoga.developerslive.data.api.ApiService
import com.ogamoga.developerslive.domain.model.ApiItem
import com.ogamoga.developerslive.domain.model.ApiPage
import com.ogamoga.developerslive.domain.model.SectionType
import com.ogamoga.developerslive.exception.LostConnectionException
import com.ogamoga.developerslive.exception.WrongResponseException

class RemoteRepository(
    private val apiService: ApiService
) {
    fun getPage(page: Int, sectionType: SectionType): ApiPage {
        val response = getPageResponse(sectionType.name.lowercase(), page)

        if (response.isSuccessful) {
            return response.body()!!
        } else throw WrongResponseException(response.message())
    }

    fun getRandomItem(): ApiItem {
        val response = getRandomItemResponse()

        if (response.isSuccessful) {
            return response.body()!!
        } else throw WrongResponseException(response.message())
    }

    private fun getPageResponse(section: String, page: Int) = try {
        apiService.getPage(section, page).execute()
    } catch (e: Exception) {
        throw LostConnectionException()
    }

    private fun getRandomItemResponse() = try {
        apiService.getRandomItem().execute()
    } catch (e: Exception) {
        throw LostConnectionException()
    }
}
