package models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(foreignKeys = [
    ForeignKey(entity = Customer::class, parentColumns = ["id"], childColumns = ["customerId"]),
    ForeignKey(entity = Flight::class, parentColumns = ["id"], childColumns = ["flightId"])
],
    indices = [androidx.room.Index(value = ["customerId"]), androidx.room.Index(value = ["flightId"])]
    )
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val customerId: Int,
    val flightId: Int,
    val bookingTime: LocalDateTime
)