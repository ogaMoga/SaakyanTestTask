package com.ogamoga.developerslive.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ogamoga.developerslive.data.database.dao.Dao
import com.ogamoga.developerslive.data.database.model.ItemsEntity
import com.ogamoga.developerslive.data.database.model.LinksEntity
import com.ogamoga.developerslive.data.database.model.SectionsEntity

@Database(entities =
    [ItemsEntity::class,
    LinksEntity::class,
    SectionsEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: Dao
}
