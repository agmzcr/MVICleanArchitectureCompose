package dev.agmzcr.mvicleanusersapp.presentation.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.agmzcr.mvicleanusersapp.domain.model.User
import dev.agmzcr.mvicleanusersapp.domain.usecase.AddUserUseCase
import dev.agmzcr.mvicleanusersapp.domain.usecase.DeleteUserUseCase
import dev.agmzcr.mvicleanusersapp.domain.usecase.GetAllUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUser: DeleteUserUseCase
) : ViewModel() {

    var loading by mutableStateOf(false)
        private set

    val users: LiveData<List<User>> by lazy {
        getAllUsersUseCase.invoke()
    }

    fun addUser() {
        if (!loading)
            viewModelScope.launch(Dispatchers.IO) {
                loading = true
                Log.i("TESTVIEWMODEL", "addUser()")
                addUserUseCase.invoke()
                loading = false
            }
    }

    fun deleteUser(toDelete: User) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUser.invoke(toDelete)
        }
    }
}
