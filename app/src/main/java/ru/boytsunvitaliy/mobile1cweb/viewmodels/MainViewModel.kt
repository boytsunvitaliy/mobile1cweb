package ru.boytsunvitaliy.mobile1cweb.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.boytsunvitaliy.mobile1cweb.models.AuthenticationState

class MainViewModel : ViewModel() {
    var authToken = ""
    val authenticationState = MutableLiveData<AuthenticationState>()
    init {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authToken = ""
        authenticationState.postValue(AuthenticationState.UNAUTHENTICATED)
    }
    fun logout() {
        authToken = ""
        authenticationState.postValue(AuthenticationState.UNAUTHENTICATED)
    }

    fun authenticate(authToken: String) {
        if (passwordIsValidForUsername(authToken)) {
            this.authToken = authToken
            authenticationState.postValue(AuthenticationState.AUTHENTICATED)
        } else {
            authenticationState.postValue(AuthenticationState.INVALID_AUTHENTICATION)
        }
    }

    private fun passwordIsValidForUsername(authToken: String): Boolean {
        return authToken.isNotEmpty()
    }
}