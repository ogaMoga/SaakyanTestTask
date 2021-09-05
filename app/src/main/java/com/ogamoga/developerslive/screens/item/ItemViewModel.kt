package com.ogamoga.developerslive.screens.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogamoga.developerslive.domain.model.ItemResource
import com.ogamoga.developerslive.domain.model.SectionType
import com.ogamoga.developerslive.domain.model.Status
import com.ogamoga.developerslive.domain.usecase.ItemUseCase
import kotlinx.coroutines.launch

class ItemViewModel(
    private val useCase: ItemUseCase,
) : ViewModel() {
    private val _itemLiveData = MutableLiveData<ItemResource>()

    val itemLiveData: LiveData<ItemResource> = _itemLiveData

    fun loadLast(sectionType: SectionType) {
        viewModelScope.launch {
            postLoading(sectionType)
            val result = useCase.getLast(sectionType)
            postLiveData(result)
        }
    }

    fun loadPrevious(currentId: Int, sectionType: SectionType) {
        viewModelScope.launch {
            postLoading(sectionType)
            val result = useCase.getPrevious(currentId, sectionType)
            postLiveData(result)
        }
    }

    fun loadCurrent(currentId: Int, sectionType: SectionType) {
        viewModelScope.launch {
            postLoading(sectionType)
            val result = useCase.getCurrent(currentId, sectionType)
            postLiveData(result)
        }
    }

    fun loadNext(currentId: Int, sectionType: SectionType) {
        viewModelScope.launch {
            postLoading(sectionType)
            val result = useCase.getNext(currentId, sectionType)
            postLiveData(result)
        }
    }

    private fun postLoading(sectionType: SectionType) {
        postLiveData(ItemResource(Status.LOADING, null, sectionType))
    }

    private fun postLiveData(itemResource: ItemResource) {
        _itemLiveData.postValue(itemResource)
    }
}