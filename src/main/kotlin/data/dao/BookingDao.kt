package data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BookingDao {

    @Query("SELECT * FROM Booking")
    suspend fun getAllBookings(): List<models.Booking>

    @Query("SELECT * FROM Booking WHERE id = :id")
    suspend fun getBookingById(id: Int): models.Booking

    @Upsert
    suspend fun insertBooking(booking: models.Booking)

    @Delete
    suspend fun deleteBooking(booking: models.Booking)


}