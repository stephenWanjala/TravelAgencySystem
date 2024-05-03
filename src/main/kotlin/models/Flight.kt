package models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Entity
data class Flight(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val flightNumber: String,
    val airline: String,
    val departureLocation: String,
    val arrivalLocation: String,
    @TypeConverters(Converters::class)
    val departureTime: LocalDateTime,
  val availableSeats: Int,
    val price: Double,
    val arrivalTime: LocalDateTime,
    val seatsAvailable: Int,
    val status: FlightStatus = FlightStatus.AVAILABLE
){
    init {
        require(availableSeats >= 0) { "Available seats must be greater than or equal to 0" }
        require(price >= 0.0) { "Price must be greater than or equal to 0" }
        require(departureTime.isBefore(arrivalTime)) { "Departure time must be before arrival time" }

    }
}


class Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            return LocalDateTime.parse(it, formatter)
        }
    }

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }
}