package models

import exceptions.ParkingLotFullException

class ParkingSpots(capacity: Int = 100) {
    private val freeSpots = Array(capacity) { it }.toMutableSet()

    fun getFreeSpotNumber(): Int {
        if (freeSpots.isEmpty()) throw ParkingLotFullException()
        return freeSpots.first()
    }

    fun reserveSpot(spotNumber: Int) {
        freeSpots.remove(spotNumber)
    }
}
