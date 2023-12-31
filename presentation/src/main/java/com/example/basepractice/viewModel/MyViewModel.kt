package com.example.basepractice.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.basepractice.base.BaseViewModel
import com.example.domain.model.MyTestData
import com.example.domain.state.Failure
import com.example.domain.state.ResourceState
import com.example.domain.useCase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

import javax.inject.Inject

/**
 * 2023-10-10
 * pureum
 */

@HiltViewModel
class MyViewModel @Inject constructor(
    private val useCase: UseCase
) : BaseViewModel() {

    @Inject
    lateinit var useCase2: UseCase



    val loading: MutableLiveData<Boolean> get() = isLoading

    private val  _myStateFlow = MutableSharedFlow<ResourceState<MyTestData>>()
    val myStateFlow : SharedFlow<ResourceState<MyTestData>> get() = _myStateFlow

    fun getApiData(placeName: String) {
        useCase(placeName).onEach { data ->
            Log.e("TAG", "viewModel 데이터 : $data", )
            when(data){
                is ResourceState.Success -> {
                    _myStateFlow.emit(data)
                    isLoading.postValue(false)
                }
                is ResourceState.Error -> {
                    _myStateFlow.emit(data)
                    isLoading.postValue(false)
                }
                else -> {
                    isLoading.postValue(true)
                }
            }
        }.launchIn(modelScope)
    }
}