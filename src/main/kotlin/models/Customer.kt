package models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val name:String,
    val email:String,
    val phoneNumber:String,
){
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
        require(email.isNotBlank()) { "Email must not be blank" }
        require(phoneNumber.isNotBlank()) { "Phone number must not be blank" }
        require(isValidEmail(email)) { "Email is not valid" }
    }

//    validate email
private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return emailRegex.toRegex().matches(email)
    }
}