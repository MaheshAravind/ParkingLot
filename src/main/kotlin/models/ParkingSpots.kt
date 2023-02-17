package models

import exceptions.ParkingLotFullException

class ParkingSpots(capacity: Int = 100) {
    private val freeSpots = Array(capacity) { it }.toMutableSet()

    fun findAndReserveFreeSpot(): Int {
        if (freeSpots.isEmpty()) throw ParkingLotFullException()
        return freeSpots.first().also { freeSpots.remove(it) }
    }
}
