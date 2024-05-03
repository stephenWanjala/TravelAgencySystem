package data.dao

import androidx.room.*
import models.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM Customer")
    suspend fun getAllCustomers(): List<Customer>

    @Query("SELECT * FROM Customer WHERE id = :id")
    suspend fun getCustomerById(id: Int): Customer

    @Upsert
    suspend fun insertCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)
}