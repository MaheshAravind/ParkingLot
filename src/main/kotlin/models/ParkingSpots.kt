package models

import exceptions.ParkingLotFullException
import exceptions.SpotAlreadyFreeException

class ParkingSpots(private val capacity: Int = 100) {
    private val freeSpots = Array(capacity) { it + 1 }.toMutableSet()

    fun findAndReserveFreeSpot(): Int {
        if (freeSpots.isEmpty()) throw ParkingLotFullException()
        return freeSpots.first().also { freeSpots.remove(it) }
    }

    fun freeSpot(spotNumber: Int) {
        if (freeSpots.contains(spotNumber)) throw SpotAlreadyFreeException()
        if (spotNumber > capacity) throw IllegalArgumentException()

        freeSpots.add(spotNumber)
    }
}
