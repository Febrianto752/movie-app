package com.example.movieapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movieapp.MovieApplication
import com.example.movieapp.ui.viewModels.user.UserViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
//        initializer {
//            ItemEditViewModel(
//                this.createSavedStateHandle()
//            )
//        }
//        // Initializer for ItemEntryViewModel
//        initializer {
//            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
//        }
//
//
//        // Initializer for ItemDetailsViewModel
//        initializer {
//            ItemDetailsViewModel(
//                this.createSavedStateHandle()
//            )
//        }
//
        // Initializer for HomeViewModel
//        initializer {
//            RegistrationViewModel(movieApplication().container.usersRepository)
//        }
//
        initializer {
            UserViewModel(movieApplication().container.usersRepository)
        }
//
//        // Initializer for HomeViewModel
//        initializer {
//            LoginViewModel(
//                movieApplication().container.usersRepository,
//                movieApplication().container.signedRepository
//            )
//        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.movieApplication(): MovieApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
