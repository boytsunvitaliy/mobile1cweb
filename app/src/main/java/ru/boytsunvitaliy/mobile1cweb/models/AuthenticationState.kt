package ru.boytsunvitaliy.mobile1cweb.models

enum class AuthenticationState {
    AUTHENTICATED,          // Initial state, the user needs to authenticate
    UNAUTHENTICATED,        // The user has authenticated successfully
    INVALID_AUTHENTICATION  // Authentication failed
}