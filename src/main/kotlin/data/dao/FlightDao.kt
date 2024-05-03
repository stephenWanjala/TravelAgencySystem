package data.dao

import androidx.room.*
import models.Flight

@Dao
interface FlightDao {

    @Query("SELECT * FROM Flight")
    suspend fun getAllFlights(): List<Flight>

    @Query("SELECT * FROM Flight WHERE id = :id")
    suspend fun getFlightById(id: Int): Flight

    @Upsert
    suspend fun insertFlight(flight: Flight)


    @Delete
    suspend fun deleteFlight(flight: Flight)

    @Insert
    suspend fun addAllFlights(vararg flights: Flight)

    @Query("SELECT * FROM Flight WHERE departureLocation = :departureLocation")
    suspend fun getFlightsByDepartureLocation(departureLocation: String): List<Flight>

    @Query("SELECT * FROM Flight WHERE arrivalLocation = :arrivalLocation")
    suspend fun getFlightsByArrivalLocation(arrivalLocation: String): List<Flight>

}