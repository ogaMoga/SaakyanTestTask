package com.ogamoga.developerslive.domain.model

data class ItemResource(
    val status: Status,
    val item: Item?,
    val sectionType: SectionType
)
