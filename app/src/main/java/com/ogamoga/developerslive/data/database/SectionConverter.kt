package com.ogamoga.developerslive.data.database

import androidx.room.TypeConverter
import com.ogamoga.developerslive.domain.model.SectionType

class SectionConverter {
    @TypeConverter
    fun toHealth(value: Int) = enumValues<SectionType>()[value]

    @TypeConverter
    fun fromHealth(value: SectionType) = value.ordinal
}
