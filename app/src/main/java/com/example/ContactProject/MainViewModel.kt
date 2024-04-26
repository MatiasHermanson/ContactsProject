package com.example.ContactProject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository = ProductRepository(application)
    private val allContacts: LiveData<List<Product>>? = repository.allContacts
    private val searchResults: MutableLiveData<List<Product>> = repository.searchResults
    private val ascContacts: LiveData<List<Product>>? = repository.allContactsASC
    private val descContacts: LiveData<List<Product>>? = repository.allContactsDESC

    fun insertContact(contact: Product) {
        repository.insertContact(contact)
    }
    fun findContact(name: String) {
        repository.findContact(name)
    }

    fun deleteContact(id: Int) {
        repository.deleteContact(id)
    }

    fun getSearchResults(): MutableLiveData<List<Product>> {
        return searchResults
    }

    fun getAllContacts(): LiveData<List<Product>>? {
        return allContacts
    }

    fun getAllContactsASC(): LiveData<List<Product>>? {
        return ascContacts
    }

    fun getAllContactsDesc(): LiveData<List<Product>>? {
        return descContacts
    }
}