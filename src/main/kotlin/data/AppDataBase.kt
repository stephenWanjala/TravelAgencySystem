package data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import models.Converters

@Database(entities = [models.Customer::class, models.Flight::class, models.Booking::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDataBase:RoomDatabase() {
    abstract fun customerDao(): data.dao.CustomerDao
    abstract fun flightDao(): data.dao.FlightDao
    abstract fun bookingDao(): data.dao.BookingDao

}