package com.ogamoga.developerslive.data.repository

import com.ogamoga.developerslive.data.database.dao.Dao
import com.ogamoga.developerslive.data.database.model.ItemsEntity
import com.ogamoga.developerslive.data.database.model.LinksEntity
import com.ogamoga.developerslive.data.database.model.SectionsEntity
import com.ogamoga.developerslive.domain.model.Item
import com.ogamoga.developerslive.domain.model.SectionType
import com.ogamoga.developerslive.exception.DatabaseException

class LocalRepository(
    private val dao: Dao
) {
    suspend fun getLast(sectionType: SectionType): Item {
        val result = dao.getLastItem(sectionType)

        if (result != null && result.id != 0) {
            return itemFromEntity(result, sectionType)
        } else {
            throw DatabaseException()
        }
    }

    suspend fun getNext(currentId: Int, sectionType: SectionType): Item {
        val result = dao.getNext(currentId, sectionType)

        if (result != null && result.id != 0) {
            return itemFromEntity(result, sectionType)
        } else {
            throw DatabaseException()
        }
    }

    suspend fun getCurrent(currentId: Int, sectionType: SectionType): Item {
        val result = dao.getCurrent(currentId, sectionType)
        return itemFromEntity(result, sectionType)
    }

    suspend fun getPrevious(currentId: Int, sectionType: SectionType): Item {
        val result = dao.getPrevious(currentId, sectionType)
        return itemFromEntity(result, sectionType)
    }

    suspend fun addItems(items: List<Item>, sectionType: SectionType) {
        val resultItems = mutableListOf<ItemsEntity>()
        val resultLinks = mutableListOf<LinksEntity>()

        val lastLink = dao.getLastLink(sectionType)
        if (lastLink != null) {
            resultLinks.add(
                LinksEntity(
                    id = lastLink.id,
                    type = sectionType,
                    nextId = items.first().id
                )
            )
        }

        var nextId: Int? = null
        for (item in items.reversed()) {
            resultItems.add(
                ItemsEntity(
                    id = item.id,
                    url = item.url,
                    description = item.description
                )
            )
            resultLinks.add(
                LinksEntity(
                    id = item.id,
                    type = sectionType,
                    nextId = nextId
                )
            )
            nextId = item.id
        }
        dao.addItems(resultItems)
        dao.addLinks(resultLinks)
    }

    suspend fun incrementAndGetPageNumber(sectionType: SectionType): Int {
        val section = dao.getSection(sectionType)
        return if (section == null || section.pagesCount == 0) {
            setSectionLimit(sectionType, 0, 1)
            0
        } else {
            val newValue = (section.currentPage + 1) % section.pagesCount
            dao.setSection(
                SectionsEntity(
                    type = sectionType,
                    currentPage = newValue,
                    pagesCount = section.pagesCount
                )
            )
            newValue
        }
    }

    suspend fun setSectionLimit(sectionType: SectionType, currentPage: Int, pagesCount: Int) {
        dao.setSection(SectionsEntity(
            type = sectionType,
            currentPage = currentPage,
            pagesCount = pagesCount
        ))
    }

    private suspend fun itemFromEntity(entity: ItemsEntity, sectionType: SectionType) =
        Item(
            id = entity.id,
            url = entity.url,
            description = entity.description,
            hasPrevious = dao.hasPrevious(entity.id, sectionType)
        )
}
