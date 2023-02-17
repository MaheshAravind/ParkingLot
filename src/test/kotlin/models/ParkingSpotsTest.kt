package models

import exceptions.ParkingLotFullException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertIs

class ParkingSpotsTest {
    @Test
    fun `should get spot number of free spot`() {
        val parkingSpots = ParkingSpots(1)

        val spotNumber = parkingSpots.findAndReserveFreeSpot()

        assertIs<Int>(spotNumber)
    }

    @Test
    fun `should not be able to reserve spot if parking lot is full`() {
        val parkingSpots = ParkingSpots(0)

        assertThrows(ParkingLotFullException::class.java) { parkingSpots.findAndReserveFreeSpot() }
    }

    @Test
    fun `should be able to free spot`() {
        val parkingSpots = ParkingSpots(1)

        val spotNumber = parkingSpots.findAndReserveFreeSpot()

        assertDoesNotThrow { parkingSpots.freeSpot(spotNumber) }
    }

    @Test
    fun `should not be able to free spot that does not exist`() {
        val parkingSpots = ParkingSpots(1)

        assertThrows(IllegalArgumentException::class.java) { parkingSpots.freeSpot(2) }
    }
}