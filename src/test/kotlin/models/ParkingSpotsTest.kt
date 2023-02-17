package models

import exceptions.ParkingLotFullException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertIs

class ParkingSpotsTest {
    @Test
    fun `should get spot number of free spot`() {
        val parkingSpots = ParkingSpots()

        val freeSpotNumber = parkingSpots.getFreeSpotNumber()

        assertIs<Int>(freeSpotNumber)
    }

    @Test
    fun `should be able to reserve spot`() {
        val parkingSpots = ParkingSpots()
        val freeSpotNumber = parkingSpots.getFreeSpotNumber()

        assertDoesNotThrow { parkingSpots.reserveSpot(freeSpotNumber) }
    }

    @Test
    fun `should not be able to reserve spot if parking lot is full`() {
        val parkingSpots = ParkingSpots(0)

        assertThrows(ParkingLotFullException::class.java) { parkingSpots.getFreeSpotNumber() }
    }
}