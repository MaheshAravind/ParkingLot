package models

class ParkingSpots(capacity: Int = 100) {
    private val freeSpots = Array(capacity) { it }.toMutableSet()

    fun getFreeSpotNumber(): Int {
        return 1
    }
}
