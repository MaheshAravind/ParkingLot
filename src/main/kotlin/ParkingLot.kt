import entities.ParkingTicket
import models.ParkingSpots
import java.util.*

class ParkingLot() {
    private var ticketNumber = 1
    private val parkingSpots: ParkingSpots = ParkingSpots()

    fun park(entryDateTime: Date = Date()): ParkingTicket {
        val newTicketNumber = generateTicketNumber()
        val spotNumber = parkingSpots.findAndReserveFreeSpot()

        return ParkingTicket(newTicketNumber, spotNumber, entryDateTime)
    }

    private fun generateTicketNumber() = ticketNumber++
}