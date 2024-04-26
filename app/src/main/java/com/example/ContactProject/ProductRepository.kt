package com.example.ContactProject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*



class ProductRepository (application: Application) {
    val searchResults = MutableLiveData<List<Product>>()
    private var productDao: ProductDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Product>>?
    val allContactsDESC: LiveData<List<Product>>?
    val allContactsASC: LiveData<List<Product>>?

    init {
        val db: ProductRoomDatabase? =
            ProductRoomDatabase.getDatabase(application)
        productDao = db?.productDao()
        allContacts = productDao?.getAllContacts()
        allContactsASC = productDao?.getAllContactsASC()
        allContactsDESC = productDao?.getAllContactsDESC()
    }

    fun insertContact(newcontact: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newcontact)
        }
    }

    private suspend fun asyncInsert(contact: Product) {
        productDao?.insertContact(contact)
    }

    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }

    private suspend fun asyncDelete(id: Int) {
        productDao?.deleteContact(id)
    }

    fun findContact(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            val foundContact: List<Product>? = asyncFind(name).await()
        }
    }

    private suspend fun asyncFind(name: String): Deferred<List<Product>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao?.findContact(name)
        }

    fun getAllContactsASC() {
        coroutineScope.launch(Dispatchers.Main) {
            val allContactsASC: LiveData<List<Product>>? = asyncASC().await()
        }
    }
    private suspend fun asyncASC() : Deferred<LiveData<List<Product>>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao?.getAllContactsASC()
        }

    fun getAllContactsDESC() {
        coroutineScope.launch(Dispatchers.Main) {
            val allContactsDESC: LiveData<List<Product>>? = asyncDESC().await()
        }
    }
    private suspend fun asyncDESC() : Deferred<LiveData<List<Product>>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao?.getAllContactsDESC()
        }
}