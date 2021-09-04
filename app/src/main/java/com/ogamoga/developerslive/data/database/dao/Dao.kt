package com.ogamoga.developerslive.data.database.dao

import androidx.room.*
import androidx.room.Dao
import com.ogamoga.developerslive.data.database.SectionConverter
import com.ogamoga.developerslive.data.database.model.ItemsEntity
import com.ogamoga.developerslive.data.database.model.LinksEntity
import com.ogamoga.developerslive.data.database.model.SectionsEntity
import com.ogamoga.developerslive.domain.model.SectionType

@TypeConverters(SectionConverter::class)
@Dao
interface Dao {
    @Query("SELECT id, url, description FROM items NATURAL JOIN links WHERE id = :currentId AND type = :sectionType LIMIT 1")
    suspend fun getCurrent(currentId: Int, sectionType: SectionType): ItemsEntity

    @Query("SELECT id, url, description FROM items NATURAL JOIN links WHERE nextId = :currentId AND type = :sectionType LIMIT 1")
    suspend fun getPrevious(currentId: Int, sectionType: SectionType): ItemsEntity

    @Query("SELECT items.id, coalesce(items.url, \"\") as url, coalesce(items.description, \"\") as description FROM links LEFT JOIN items ON items.id = (SELECT nextId FROM links WHERE id = :currentId AND type = :sectionType) LIMIT 1")
    suspend fun getNext(currentId: Int, sectionType: SectionType): ItemsEntity?

    @Query("SELECT id, url, description FROM items NATURAL JOIN links WHERE nextId IS NULL AND type = :sectionType LIMIT 1")
    suspend fun getLastItem(sectionType: SectionType): ItemsEntity?

    @Query("SELECT * FROM links WHERE nextId IS NULL AND type = :sectionType LIMIT 1")
    suspend fun getLastLink(sectionType: SectionType): LinksEntity?

    @Query("SELECT * FROM sections WHERE type = :sectionType LIMIT 1")
    suspend fun getSection(sectionType: SectionType): SectionsEntity?

    @Query("SELECT EXISTS(SELECT id FROM links WHERE nextId = :currentId AND type = :sectionType)")
    suspend fun hasPrevious(currentId: Int, sectionType: SectionType): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSection(section: SectionsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItems(items: List<ItemsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLinks(list: List<LinksEntity>)
}
