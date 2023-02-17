package models

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class ParkingSpotsTest {
    @Test
    fun `should get spot number of free spot`() {
        val parkingSpots = ParkingSpots()

        val freeSpotNumber = parkingSpots.getFreeSpotNumber()

        assertIs<Int>(freeSpotNumber)
    }


}