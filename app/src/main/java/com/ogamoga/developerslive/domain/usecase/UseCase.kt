package com.ogamoga.developerslive.domain.usecase

import com.ogamoga.developerslive.data.repository.LocalRepository
import com.ogamoga.developerslive.data.repository.RemoteRepository
import com.ogamoga.developerslive.domain.model.*
import com.ogamoga.developerslive.exception.DatabaseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UseCase(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) {
     suspend fun getLast(sectionType: SectionType): ItemResource = withContext(Dispatchers.IO) {
        try {
            return@withContext ItemResource(Status.SUCCESS, localRepository.getLast(sectionType), sectionType)
        } catch (e: DatabaseException) {
            return@withContext loadAndUpdate(sectionType, true)
        }
    }

    suspend fun getNext(currentId: Int, sectionType: SectionType): ItemResource = withContext(Dispatchers.IO) {
        try {
            return@withContext ItemResource(Status.SUCCESS,localRepository.getNext(currentId, sectionType), sectionType)
        } catch (e: DatabaseException) {
            return@withContext loadAndUpdate(sectionType, false)
        }
    }

    suspend fun getCurrent(currentId: Int, sectionType: SectionType): ItemResource = withContext(Dispatchers.IO) {
        return@withContext ItemResource(Status.SUCCESS, localRepository.getCurrent(currentId, sectionType), sectionType)
    }

    suspend fun getPrevious(currentId: Int, sectionType: SectionType): ItemResource = withContext(Dispatchers.IO) {
        return@withContext ItemResource(Status.SUCCESS, localRepository.getPrevious(currentId, sectionType), sectionType)
    }

    private suspend fun loadAndUpdate(sectionType: SectionType, isFirstLoad: Boolean): ItemResource {
        val currentPage = localRepository.incrementAndGetPageNumber(sectionType)
        val remoteData: ApiResponse?
        val result = mutableListOf<Item>()
        try {
            remoteData = remoteRepository.getNextPage(currentPage, sectionType)
            for (item in remoteData.result) {
                result.add(Item(
                    id = item.id,
                    url = item.gifURL.replace("http", "https", true),
                    description = item.description,
                    true
                ))
            }
            localRepository.setSectionLimit(sectionType, currentPage, remoteData.totalCount / 5)

            return if (result.isEmpty()) {
                ItemResource(Status.ERROR, null, sectionType)
            } else {
                result[0].hasPrevious = !isFirstLoad
                localRepository.addItems(result, sectionType)
                ItemResource(Status.SUCCESS, result[0], sectionType)
            }
        } catch(e: Exception) { return ItemResource(Status.ERROR, null, sectionType) }
    }
}

