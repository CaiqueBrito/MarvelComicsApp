package com.marvelcomics.brito.viewmodel.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvelcomics.brito.domain.exception.NetworkException
import com.marvelcomics.brito.domain.usecase.ComicUseCase
import com.marvelcomics.brito.viewmodel.ComicUiState
import com.marvelcomics.brito.viewmodel.GlobalUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ComicViewModel(
    private val comicUseCase: ComicUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _comicUiState = MutableStateFlow<Any>(GlobalUiState.Empty)
    val comicUiState: StateFlow<Any> = _comicUiState

    fun loadComics(id: Int) =
        viewModelScope.launch(dispatcher) {
            _comicUiState.value = GlobalUiState.Loading
            comicUseCase.getComics(id).catch {
                if (it is NetworkException) {
                    _comicUiState.value = GlobalUiState.NetworkError
                } else {
                    _comicUiState.value = ComicUiState.Error(it)
                }
            }.collect {
                _comicUiState.value = ComicUiState.Success(it)
            }
        }
}
