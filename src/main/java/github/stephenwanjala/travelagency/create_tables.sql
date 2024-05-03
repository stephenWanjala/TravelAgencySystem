create database if not exists travel_agency;
use travel_agency;
-- Create Customers table
CREATE TABLE Customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL
);

-- Create Flights table
CREATE TABLE Flights (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         origin VARCHAR(255) NOT NULL,
                         destination VARCHAR(255) NOT NULL,
                         available_seats INT NOT NULL
);

-- Create Bookings table
CREATE TABLE Bookings (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          customer_id INT NOT NULL,
                          flight_id INT NOT NULL,
                          FOREIGN KEY (customer_id) REFERENCES Customers(id),
                          FOREIGN KEY (flight_id) REFERENCES Flights(id)
);
