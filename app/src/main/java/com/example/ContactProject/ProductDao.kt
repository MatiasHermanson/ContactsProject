package com.example.ContactProject


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    fun insertContact(contact: Product)
    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    fun findContact(name: String): List<Product>
    @Query("DELETE FROM contacts WHERE contactId = :id")
    fun deleteContact(id: Int)
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Product>>
    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    fun getAllContactsASC(): LiveData<List<Product>>
    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    fun getAllContactsDESC(): LiveData<List<Product>>
}