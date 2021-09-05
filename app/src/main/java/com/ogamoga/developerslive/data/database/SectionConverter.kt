package com.ogamoga.developerslive.data.database

import androidx.room.TypeConverter
import com.ogamoga.developerslive.domain.model.SectionType

class SectionConverter {
    @TypeConverter
    fun toSectionType(value: Int) = enumValues<SectionType>()[value]

    @TypeConverter
    fun fromSectionType(value: SectionType) = value.ordinal
}
